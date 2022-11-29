package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class CompanyRepositoryTest {

    @RepeatedTest(1)
    void findById() {
        //given
        CompanyRepository repository = new CompanyRepository(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Company company = repository.findById(id);

        //when
        boolean isIdEqual = (Objects.equals(company.getId(), id));
        System.out.println(company);

        //then
       Assertions.assertTrue(isIdEqual);
        }


    @Test
    void findAll() {
            CompanyRepository repository = new CompanyRepository(HibernateUtil.getSessionFactory());
            List<Company> companies = repository.findAll();
             for (Company c: companies
                          ) {
                     System.out.println(c.toString());
                 }

            //when
            boolean isNotEmpty = (companies.isEmpty());

            //then
            Assertions.assertFalse(isNotEmpty);
        }


    @Test
    void save() {
        //given
        Company first = new Company(1001L, "Bank", "76765779", false, 235L, null, null);
        CompanyRepository repository = new CompanyRepository(HibernateUtil.getSessionFactory());
        repository.save(first);
        Company second = repository.findById(1001L);
        repository.delete(1001L);

        //when
        boolean isEqual = (Objects.equals(first.getTaxNumber(), second.getTaxNumber()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    void update() {
        //given
        CompanyRepository repository = new CompanyRepository(HibernateUtil.getSessionFactory());
        long id = (long) (Math.random() * 1000 + 1);
        Company first = repository.findById(id);
        repository.update(new Company(1001L, "Bank", "76765779", false, 235L, null, null));
        Company second = repository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        repository.update(first);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    void delete() {

        //given
        CompanyRepository repository = new CompanyRepository(HibernateUtil.getSessionFactory());
        Company company = new Company(1005L, "ENEKA", "333444999", false, 342L, null, null);
        repository.save(company);
        Company first = repository.findById(1005L);
        boolean isExist = first.getId() != null;
        repository.delete(1005L);
        Company second = repository.findById(1005L);

        //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);
    }
}