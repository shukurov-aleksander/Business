package com.cu.business.DAO;

import com.ku.business.DAO.GetFromTable;
import com.ku.business.entity.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetFromTableTest {
    @Test
    public void testIsGetFromTableReturnSomething() {
        //given
        List<Company> companies = new ArrayList<>();
        GetFromTable getFromTable = new GetFromTable();
        companies = getFromTable.selectFromTableCompany("companies");
        for (Company company: companies) {
            System.out.println(company.toString());
        }
        //when
        boolean isNotEmpty = (companies.isEmpty());

        //then
        Assertions.assertFalse(isNotEmpty);
    }

//    @Test
//    public void testReturnId(){
//        //в дальнейшем буду сравнивать с Company.getId()
//        //given
//        GetFromTable getFromTable = new GetFromTable();
//        String result = getFromTable.selectFromTable("companies", "1","id", "company_name", "tax_number");
//        int id = Character.getNumericValue((result.charAt(1)));
//
//        //when
//        boolean isIdEqual = (id == 1);
//
//        //then
//        Assertions.assertTrue(isIdEqual);
//
//    }
}
