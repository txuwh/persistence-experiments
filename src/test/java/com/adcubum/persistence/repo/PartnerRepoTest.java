package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Partner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PartnerRepoTest {

    @Autowired
    private PartnerRepo sut;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void test() {
//        sessionFactory.getCurrentSession().enableFilter("state").setParameter("keyDate", new Date());


        Iterable<Partner> partners = sut.findAll();
        assertThat(partners).isNotEmpty();
    }

}
