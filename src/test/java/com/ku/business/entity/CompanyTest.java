package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CompanyTest {

    @Test
    public void testEqualsWhenIdNull() {
        //given
        Storage firstStorage = new Storage(null, 255, null, null);
        Storage secondStorage = new Storage(1L, 172, null, null);
        List<Storage> storages = new ArrayList<>();
        storages.add(firstStorage);
        storages.add(secondStorage);
        Detail firstDetail = new Detail(null, null, null);
        Detail secondDetail = new Detail(1L, null, null);
        List<Detail> details = new ArrayList<>();
        details.add(firstDetail);
        details.add(secondDetail);
        Company first = new Company(null, "Bank", "124325", true, 1L, null, details);
        Company second = new Company(null, "Bank", "124321", true, 1L, storages, null);

        //when
        boolean isEqual = first.equals(second);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testHashCodeWhenIdNull() {
        //given
        Storage firstStorage = new Storage(null, 255, null, null);
        Storage secondStorage = new Storage(1L, 172, null, null);
        List<Storage> storages = new ArrayList<>();
        storages.add(firstStorage);
        storages.add(secondStorage);
        Detail firstDetail = new Detail(null, null, null);
        Detail secondDetail = new Detail(1L, null, null);
        List<Detail> details = new ArrayList<>();
        details.add(firstDetail);
        details.add(secondDetail);
        Company first = new Company(null, "Bank", "124325", true, 1L, null, details);
        Company second = new Company(null, "Bank", "124321", true, 1L, storages, null);

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testHashCodeCollisionForDifferentObjects() {
        //given
        List<Storage> storages = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        Company first = new Company(1L, "Bank", "124325", true, 1L, null, details);
        Company second = new Company(1L, "Bank", "124325", true, 1L, storages, null);

        //when
        boolean isHashCodeEqual = first.hashCode() == second.hashCode();
        boolean isObjectsEqual = first.equals(second);

        //then
        Assertions.assertTrue(isHashCodeEqual);
        Assertions.assertFalse(isObjectsEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Storage firstStorage = new Storage(1L, 255, null, null);
        Storage secondStorage = new Storage(2L, 172, null, null);
        List<Storage> storages = new ArrayList<>();
        storages.add(firstStorage);
        storages.add(secondStorage);
        Detail firstDetail = new Detail(null, null, null);
        Detail secondDetail = new Detail(1L, null, null);
        List<Detail> details = new ArrayList<>();
        details.add(firstDetail);
        details.add(secondDetail);
        Company company = new Company(null, "Bank", "124325", true, 1L, storages, details);

        //when
        String output = company.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}


