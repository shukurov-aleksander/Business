package com.ku.business.dao;

import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class CompanyRepositoryTest {
    public PGSimpleDataSource dataSource = new PGSimpleDataSource();
    CompanyRepository companyRepository = new CompanyRepository(getConnection());

    public DataSource getConnection() {
        this.dataSource.setServerNames(new String[] {
        "Local Business database"
    });
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8");
        this.dataSource.setDatabaseName(this.dataSource.getDatabaseName());
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("postgres");
        return dataSource;
    }

    @Test
    public void testGetListOfCompanies() throws RepositoryException {

        //given
        List<Company> companies = companyRepository.findAll();
        for (Company c: companies
             ) {
            System.out.println(c);
        }
        //when
        boolean isNotEmpty = (companies.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }
    
    @RepeatedTest(1000)
    public void testReturnCompanyById() throws RepositoryException {
        //given
        Long id = (long)( Math.random() * (1000-1) ) + 1;
        Company company = companyRepository.findById(id);
        System.out.println(company);

        //when
        boolean isIdEqual = (Objects.equals(company.getId(), id));

        //then
        Assertions.assertTrue(isIdEqual);
    }

    @Test
    public void testAddToTable() throws RepositoryException {

        //given
        Company first = new Company(1025L,"State", "36435579", false, 523L, null, null);
        companyRepository.save(first);
        Company second = companyRepository.findById(1025L);
        companyRepository.delete(1025L);
        //when
        boolean isEqual = (Objects.equals(first.getTaxNumber(), second.getTaxNumber()));

        //then
        Assertions.assertTrue(isEqual);
    }

    @Test
    public void testUpdateValueInTable() throws RepositoryException {

        //given
        long id = (long) (Math.random() * 1000 + 1);
        Company first = companyRepository.findById(id);
        companyRepository.update(new Company(id, "ENEKA", "374439", false, 373L, null, null));
        Company second = companyRepository.findById(id);

        //when
        boolean isEqual = (first.equals(second));
        companyRepository.update(first);

        //then
        Assertions.assertFalse(isEqual);

    }

    @Test
    public void testDeleteFromTable() throws RepositoryException {
        //given
        Company company = new Company(1005L,"ENEKA", "333444999", false, 342L, null, null);
        companyRepository.save(company);
        Company first = companyRepository.findById(1005L);
        boolean isExist = first.getId() != null;
    companyRepository.delete(1005L);
        Company second = companyRepository.findById(1005L);


      //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertNotEquals(isExist, isExistAfterDelete);

    }

}

