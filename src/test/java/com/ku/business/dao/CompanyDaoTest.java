package com.ku.business.dao;

import com.ku.business.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class CompanyDaoTest {

    @Test
    void findById() {
        //given
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()
        ) {
             Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            CompanyDao companyDao = new CompanyDao(sessionFactory);
            Long id = 577L;//(long) (Math.random() * (1000 - 1)) + 1;
            Company company = companyDao.findById(id);

            //when
            boolean isIdEqual = (Objects.equals(company.getId(), id));
            System.out.println(company);

            //then
            Assertions.assertTrue(isIdEqual);
            session.getTransaction().commit();
        }
    }

    @Test
    void findAll() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()
        ) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            CompanyDao companyDao = new CompanyDao(sessionFactory);
            List<Company> companies = companyDao.findAll();
            for (Company c: companies
                 ) {
                System.out.println(c);
            }

            //when
            boolean isNotEmpty = (companies.isEmpty());

            //then
            Assertions.assertFalse(isNotEmpty);
            session.getTransaction().commit();
        }
    }


    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}