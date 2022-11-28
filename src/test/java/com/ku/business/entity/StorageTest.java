package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StorageTest {
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
        Service service = new Service(1L,"sell",355L,"sell something");
        Storage first = new Storage(null,255,company,service);
        Storage second = new Storage(null,255,company,null);

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
        Service service = new Service(1L,"sell",355L,"sell something");
        Storage first = new Storage(null,255,company,service);
        Storage second = new Storage(null,255,company,null);

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
        Service service = new Service(1L,"sell",355L,"sell something");
        Storage storage = new Storage(null,255,company,service);

        //when
        String output = storage.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}
