package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DetailTest {
    List<Storage> storages = new ArrayList<>();
    List<Detail> details = new ArrayList<>();
    List<Content> contents = new ArrayList<>();
    Order order = new Order();
    Company company = new Company();

    Detail x = new Detail(1L, company, order);
    Detail y = new Detail(1L, company, order);
    Detail z = new Detail(1L, company, order);
    Detail v = new Detail(2L, company, order);
    Detail detail1 = new Detail(2L, null, order);
    Detail detail11 = new Detail(2L, null, order);
    Detail detail2 = new Detail(null, company, order);
    Detail detail22 = new Detail(null, company, order);
    Detail detail3 = new Detail(2L, null, null);
    Detail detail33 = new Detail(2L, null, null);

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
        Assertions.assertFalse(detail1.equals(detail2));
        Assertions.assertFalse(detail2.equals(detail3));
        Assertions.assertFalse(detail1.equals(detail3));
        Assertions.assertTrue(detail1.equals(detail11));
        Assertions.assertTrue(detail2.equals(detail22));
        Assertions.assertTrue(detail3.equals(detail33));
    }

    @Test
    public void testingHashCode() {
        Assertions.assertFalse(detail1.hashCode() == (detail2).hashCode());
        Assertions.assertFalse(detail2.hashCode() == (detail3).hashCode());
        Assertions.assertFalse(detail1.hashCode() == (detail3).hashCode());
        Assertions.assertTrue(detail1.hashCode() == (detail11).hashCode());
        Assertions.assertTrue(detail2.hashCode() == (detail22).hashCode());
        Assertions.assertTrue(detail3.hashCode() == (detail33).hashCode());
    }
}
