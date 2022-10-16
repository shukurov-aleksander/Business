package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    List<Storage> storages = new ArrayList<>();
    List<Detail> details = new ArrayList<>();
    Company company = new Company();
    Company company2 = new Company();
    Service service = new Service();
    Storage x = new Storage(1L,255,company,service);
    Storage y = new Storage(1L,255,company,service);
    Storage z = new Storage(1L,255,company,service);
    Storage v = new Storage(2L,365,company2,service);
    Storage storage1 = new Storage(2L,365,null,service);
    Storage storage11 = new Storage(2L,365,null,service);
    Storage storage2 = new Storage(null,365,company2,service);
    Storage storage22 = new Storage(null,365,company2,service);
    Storage storage3 = new Storage(2L,365,null,null);
    Storage storage33 = new Storage(2L,365,null,null);

    @Test
    public void equalsReflexiveTesting() {
        Detail detail = new Detail();
        Assertions.assertTrue(x.equals(x));
    }

    @Test
    public void equalsSymmetricTesting() {
        Assertions.assertTrue(x.equals(y) && y.equals(x));
    }

    @Test
    public void equalsTransitiveTesting() {
        Assertions.assertTrue(x.equals(y) && y.equals(z) && x.equals(z));
    }

    @Test
    public void equalsConsistentTesting() {
        for (int i = 0; i <= 2; i++) {
            Assertions.assertTrue(x.equals(y));
        }

    }

    @Test
    public void equalsEqualToNullTesting() {
        Assertions.assertFalse(x.equals(null));
    }

    @Test
    public void hashCodePermanentCalling() {
        Assertions.assertTrue(x.hashCode() == x.hashCode());
    }

    @Test
    public void hashCodeForEqualObjectsCalling() {
        Assertions.assertTrue(x.equals(y));
        Assertions.assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    public void equalsHashcodeDifferentObjects() {
        Assertions.assertFalse(x.equals(v) && x.hashCode() == v.hashCode());
        Assertions.assertTrue(x.equals(y) && x.hashCode() == y.hashCode());
    }

    @Test
    public void testingEquals() {
        Assertions.assertFalse(storage1.equals(storage2));
        Assertions.assertFalse(storage2.equals(storage3));
        Assertions.assertFalse(storage1.equals(storage3));
        Assertions.assertTrue(storage1.equals(storage11));
        Assertions.assertTrue(storage2.equals(storage22));
        Assertions.assertTrue(storage3.equals(storage33));
    }

    @Test
    public void testingHashCode() {
        Assertions.assertFalse(storage1.hashCode() == (storage2).hashCode());
        Assertions.assertFalse(storage2.hashCode() == (storage3).hashCode());
        Assertions.assertFalse(storage1.hashCode() == (storage3).hashCode());
        Assertions.assertTrue(storage1.hashCode() == (storage11).hashCode());
        Assertions.assertTrue(storage2.hashCode() == (storage22).hashCode());
        Assertions.assertTrue(storage3.hashCode() == (storage33).hashCode());
    }
}
