package com.cu.business.DAO;

import com.ku.business.controller.Connect;
import com.ku.business.dao.CompanyRepository;
import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class CompanyRepositoryTest {
    Connect connect = new Connect();
    CompanyRepository companyRepository = new CompanyRepository(connect.getConnection());
    @Test
    public void testGetListOfCompanies() throws RepositoryException {

        //given
        List<Company> companies = companyRepository.findAll();

        //when
        boolean isNotEmpty = (companies.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

    @Test
    public void testReturnCompanyById() throws RepositoryException {
        //given
        Company company = companyRepository.findById(333L);

        //when
        boolean isIdEqual = (company.getId() == 333);

        //then
        Assertions.assertTrue(isIdEqual);

    }

    @Test
    public void testAddToTable() throws RepositoryException {

        //given
        Company first = new Company(1025L,"State", "36435579", false, 523L, null, null);
        companyRepository.add(first);
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
        companyRepository.add(company);
        Company first = companyRepository.findById(1005L);
        boolean isExist = first.getId() != null;
      companyRepository.delete(1024L);
        Company second = companyRepository.findById(1005L);


      //when
        boolean isExistAfterDelete = second.getId() != null;

        //then
        Assertions.assertFalse(isExist == isExistAfterDelete);

    }

}

