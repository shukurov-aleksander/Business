package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ContentTest {
    List<Order> orders = new ArrayList<>();
    Service service = new Service();
    Content x = new Content(1L, 255L, service, orders);
    Content y = new Content(1L, 255L, service, orders);
    Content z = new Content(1L, 255L, service, orders);
    Content v = new Content(2L, 341L, service, orders);
    Content content1 = new Content(2L, 341L, null, orders);
    Content content2 = new Content(null, 341L, service, orders);
    Content content4 = new Content(null, 341L, service, orders);
    Content content3 = new Content(2L, 341L, null, null);

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
        Assertions.assertFalse(content1.equals(content2));
        Assertions.assertFalse(content1.equals(content3));
        Assertions.assertFalse(content2.equals(content3));
        Assertions.assertTrue(content2.equals(content4));
    }
}
