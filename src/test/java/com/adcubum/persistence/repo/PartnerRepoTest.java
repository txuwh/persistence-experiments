package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Partner;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PartnerRepoTest {

    @Autowired
    private PartnerRepo sut;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void test() throws Exception {
        Session session = entityManager.unwrap(Session.class);

        Date keyDate = new SimpleDateFormat("dd-MM-yyyy").parse("03-03-1993");

        Filter filter = session.enableFilter("state").setParameter("keyDate", keyDate);;

        Iterable<Partner> partners = sut.findAll();
        System.out.println(partners.iterator().next().states.size());
        assertThat(partners).isNotEmpty();
    }

}
