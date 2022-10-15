package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CompanyTest {
    List<Storage> storages = new ArrayList<>();
    List<Detail> details = new ArrayList<>();
    Company x = new Company(1L, "Bank", "124325", true, 1L, storages, details);
    Company y = new Company(1L, "Bank", "124325", true, 1L, storages, details);
    Company z = new Company(1L, "Bank", "124325", true, 1L, storages, details);
    Company v = new Company(2L, "Circus", "21453212", false, 2L, storages, details);
    Company company1 = new Company(2L, "Circus", "21453212", false, 2L, storages, details);
    Company company2 = new Company(1L, "Bank", "124325", true, 1L, null, details);
    Company company3 = new Company(null, "Bank", "124325", true, 1L, storages, details);
    Company company4 = new Company(1L, "Bank", "124325", true, 1L, null, null);

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
        Assertions.assertFalse(company1.equals(company2));
        Assertions.assertFalse(company2.equals(company3));
        Assertions.assertFalse(company3.equals(company4));
        Assertions.assertFalse(company1.equals(company3));
        Assertions.assertFalse(company1.equals(company4));
        Assertions.assertFalse(company2.equals(company4));
    }
}
