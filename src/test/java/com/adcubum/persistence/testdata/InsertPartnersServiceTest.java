package com.adcubum.persistence.testdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InsertPartnersServiceTest {

    @Autowired
    private InsertPartnersService sut;

    @Autowired
    private InsertAddressesService insertAddressesService;

    @Test
    public void insertTestData() {
        List<String> partnerPks = sut.insertPartnersAndReturnPks();
        insertAddressesService.insertAddresses(partnerPks);
    }

}
