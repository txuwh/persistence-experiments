package com.adcubum.persistence.testdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InsertAddressesService {

    public static final int MAX_NUMBER_OF_PARTNERS_PER_ADDRESS = 5;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Random random = new Random();

    public void insertAddresses(List<String> partnerPks) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0L));

        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 480);
        Date endDate = calendar.getTime();


        List<Object[]> addressArgs = new ArrayList<>();
        List<Object[]> addressStateArgs = new ArrayList<>();
        List<Object[]> partnerAddressArgs = new ArrayList<>();
        List<Object[]> partnerAddressStateArgs = new ArrayList<>();

        int partnerIndex = 0;

        while (partnerIndex < partnerPks.size()) {
            int numberOfPartnersForCurrentAddress = random.nextInt(MAX_NUMBER_OF_PARTNERS_PER_ADDRESS);

            String pk = UUID.randomUUID().toString();
            String description = random.nextInt(1_000_000) + "description";

            generateAddressStates(addressStateArgs, pk);

            String[] currentAddressArgs = {pk, description};
            addressArgs.add(currentAddressArgs);

            for (int i = 0; i < numberOfPartnersForCurrentAddress && partnerIndex + i < partnerPks.size(); i++) {
                String partnerAddressPk = UUID.randomUUID().toString();
                String code = random.nextInt(1_000_000) + "code";
                String[] currentPartnerAddressArgs = {partnerAddressPk, code};
                partnerAddressArgs.add(currentPartnerAddressArgs);

                String partnerAddressStatePk = UUID.randomUUID().toString();
                String partnerPk = partnerPks.get(partnerIndex + i);
                Object[] currentPartnerAddressStateArgs = {partnerAddressStatePk, partnerAddressPk, partnerPk, pk,
                        startDate, endDate};
                partnerAddressStateArgs.add(currentPartnerAddressStateArgs);
            }

            partnerIndex += numberOfPartnersForCurrentAddress;
        }

        jdbcTemplate.batchUpdate("INSERT INTO address(id, description) VALUES (?,?)", addressArgs);
        jdbcTemplate.batchUpdate("INSERT INTO address_state(id, address_id, zip_code, street, building_number, " +
                "state_begin, state_end) VALUES (?,?,?,?,?,?,?)", addressStateArgs);

        jdbcTemplate.batchUpdate("INSERT INTO partner_address(id, code) VALUES (?,?)", partnerAddressArgs);
        jdbcTemplate.batchUpdate("INSERT INTO partner_address_state(id, partner_address_id, partner_id, address_id, " +
                "state_begin, state_end) VALUES (?,?,?,?,?,?)", partnerAddressStateArgs);

    }


    private void generateAddressStates(List<Object[]> addressStateArgs, String addressId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0L));

        Date startDate = calendar.getTime();

        int intermediateMonths = random.nextInt(480);

        calendar.add(Calendar.MONTH, intermediateMonths);
        Date intermediateDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 480 - intermediateMonths);
        Date endDate = calendar.getTime();

        String pk1 = UUID.randomUUID().toString();
        String zipCode = String.valueOf(random.nextInt(1_000_000));
        String street = "Street " + random.nextInt(1_000);
        String buildingNumber = String.valueOf(random.nextInt(100));

        Object[] currentAddressStateArgs1 = {pk1, addressId, zipCode, street, buildingNumber,
                startDate, intermediateDate};
        addressStateArgs.add(currentAddressStateArgs1);

        String pk2 = UUID.randomUUID().toString();
        String buildingNumber2 = buildingNumber + "/1";

        Object[] currentAddressStateArgs2 = {pk2, addressId, zipCode, street, buildingNumber2,
                intermediateDate, endDate};
        addressStateArgs.add(currentAddressStateArgs2);

    }
}


