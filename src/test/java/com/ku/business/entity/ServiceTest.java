package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    Service x = new Service(1L, "Barter", 255L, "Some barter");
    Service y = new Service(1L, "Barter", 255L, "Some barter");
    Service z = new Service(1L, "Barter", 255L, "Some barter");
    Service v = new Service(2L, "byu", 3552L, "Some byu");
    Service service1 = new Service(2L, "byu", 3552L, null);
    Service service11 = new Service(2L, "byu", 3552L, null);
    Service service2 = new Service(null, "byu", 3552L, "Some byu");
    Service service22 = new Service(null, "byu", 3552L, "Some byu");
    Service service3 = new Service(2L, null, 3552L, null);
    Service service33 = new Service(2L, null, 3552L, null);

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
        Assertions.assertFalse(service1.equals(service2));
        Assertions.assertFalse(service2.equals(service3));
        Assertions.assertFalse(service1.equals(service3));
        Assertions.assertTrue(service1.equals(service11));
        Assertions.assertTrue(service2.equals(service22));
        Assertions.assertTrue(service3.equals(service33));
    }

    @Test
    public void testingHashCode() {
        Assertions.assertFalse(service1.hashCode() == (service2).hashCode());
        Assertions.assertFalse(service2.hashCode() == (service3).hashCode());
        Assertions.assertFalse(service1.hashCode() == (service3).hashCode());
        Assertions.assertTrue(service1.hashCode() == (service11).hashCode());
        Assertions.assertTrue(service2.hashCode() == (service22).hashCode());
        Assertions.assertTrue(service3.hashCode() == (service33).hashCode());
    }
}
