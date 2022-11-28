package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetailTest {
    @Test
    public void testEqualsWhenIdNull() {
        //given
        Storage firstStorage = new Storage(null, 255, null, null);
        Storage secondStorage = new Storage(1L, 172, null, null);
        List<Storage> storages = new ArrayList<>();
        storages.add(firstStorage);
        storages.add(secondStorage);
        Detail firstDetail = new Detail(null, null, null, null);
        Detail secondDetail = new Detail(1L, null, null, null);
        Set<Detail> details = new HashSet<>();
        details.add(firstDetail);
        details.add(secondDetail);
        Company company = new Company(null, "Bank", "124325", true, 1L, storages, details);
        Order order = new Order(1L, LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null, null);
        Detail first = new Detail(null,company,order, null);
        Detail second = new Detail(null,company,null, null);

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
        Detail firstDetail = new Detail(null, null, null, null);
        Detail secondDetail = new Detail(1L, null, null, null);
        Set<Detail> details = new HashSet<>();
        details.add(firstDetail);
        details.add(secondDetail);
        Company company = new Company(null, "Bank", "124325", true, 1L, storages, details);
        Order order = new Order(1L,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null, null);
        Detail first = new Detail(null,company,order, null);
        Detail second = new Detail(null,company,null, null);

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Storage firstStorage = new Storage(null, 255, null, null);
        Storage secondStorage = new Storage(1L, 172, null, null);
        List<Storage> storages = new ArrayList<>();
        storages.add(firstStorage);
        storages.add(secondStorage);
        Detail firstDetail = new Detail(null, null, null, null);
        Detail secondDetail = new Detail(1L, null, null, null);
        Set<Detail> details = new HashSet<>();
        details.add(firstDetail);
        details.add(secondDetail);
        Company company = new Company(null, "Bank", "124325", true, 1L, storages, details);
        Order order = new Order(1L,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null, null);
        Detail detail = new Detail(null,company,order, null);

        //when
        String output = detail.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}
