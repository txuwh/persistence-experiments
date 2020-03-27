package com.adcubum.persistence.testdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InsertPartnersService {

    public static final int NUMBER_OF_PARTNERS = 100_000;
    public static final int MAX_NUMBER_OF_PARTNER_STATES = 10;
    public static final int NUMBER_OF_DIFFERENT_NAMES = 1000;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Random random = new Random();

    public List<String> insertPartnersAndReturnPks() {
        List<String> partnerPks = new ArrayList<>();

        List<Object[]> partnerArgs = new ArrayList<>();
        List<Object[]> partnerStateArgs = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_PARTNERS; i++) {
            String pk = UUID.randomUUID().toString();
            String name = random.nextInt(NUMBER_OF_DIFFERENT_NAMES) + "name";
            partnerPks.add(pk);

            generatePartnerStates(partnerStateArgs, pk, name);

            String[] currentPartnerArgs = {pk, name};
            partnerArgs.add(currentPartnerArgs);
        }

        jdbcTemplate.batchUpdate("INSERT INTO partner(id, birth_name) VALUES (?,?)", partnerArgs);
        jdbcTemplate.batchUpdate("INSERT INTO partner_state(id, partner_id, name, state_begin, state_end) VALUES (?,?,?,?,?)", partnerStateArgs);

        return partnerPks;
    }

    private void generatePartnerStates(List<Object[]> partnerStateArgs, String partnerId, String partnerName) {
        int statesNumber = random.nextInt(MAX_NUMBER_OF_PARTNER_STATES);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0L));
        calendar.add(Calendar.MONTH, random.nextInt(480));

        for (int i = 0; i < statesNumber; i++) {
            String pk = UUID.randomUUID().toString();
            String name = partnerName + i;

            Date stateBegin = calendar.getTime();
            calendar.add(Calendar.MONTH, random.nextInt(24));
            Date stateEnd = calendar.getTime();

            Object[] currentPartnerStateArgs = {pk, partnerId, name, stateBegin, stateEnd};
            partnerStateArgs.add(currentPartnerStateArgs);
        }
    }


}
