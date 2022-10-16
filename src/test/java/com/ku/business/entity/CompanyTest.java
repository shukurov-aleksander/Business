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
    Company company1 = new Company(2L, null, "21453212", false, 2L, storages, details);
    Company company11 = new Company(2L, null, "21453212", false, 2L, storages, details);
    Company company2 = new Company(1L, "Bank", "124325", true, 1L, null, null);
    Company company22 = new Company(1L, "Bank", "124325", true, 1L, null, null);
    Company company3 = new Company(null, "Bank", "124325", true, 1L, storages, details);
    Company company33 = new Company(null, "Bank", "124325", true, 1L, storages, details);
    Company company4 = new Company(1L, "Bank", "124325", true, 1L, null, details);
    Company company44 = new Company(1L, "Bank", "124325", true, 1L, null, details);


    @Test
    public void equalsReflexiveTesting() {
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
        Assertions.assertTrue(company1.equals(company11));
        Assertions.assertTrue(company2.equals(company22));
        Assertions.assertTrue(company3.equals(company33));
        Assertions.assertTrue(company4.equals(company44));
    }

    @Test
    public void testingHashCode() {
        System.out.println(company4.hashCode());
        System.out.println(company2.hashCode());
        Assertions.assertFalse(company1.hashCode() == (company2).hashCode());
        Assertions.assertFalse(company2.hashCode() == (company3).hashCode());
        Assertions.assertFalse(company3.hashCode() == (company4).hashCode());
        Assertions.assertFalse(company1.hashCode() == (company3).hashCode());
        Assertions.assertFalse(company1.hashCode() == (company4).hashCode());
        Assertions.assertFalse(company2.hashCode() == (company4).hashCode());
        Assertions.assertTrue(company1.hashCode() == (company11).hashCode());
        Assertions.assertTrue(company2.hashCode() == (company22).hashCode());
        Assertions.assertTrue(company3.hashCode() == (company33).hashCode());
        Assertions.assertTrue(company4.hashCode() == (company44).hashCode());
    }
}
