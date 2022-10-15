package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTest {
    List<Content> contents = new ArrayList<>();
    Order x = new Order(1L,new Date(51548484466546L),new Date(51548488466546L),contents);
    Order y = new Order(1L,new Date(51548484466546L),new Date(51548488466546L),contents);
    Order z = new Order(1L,new Date(51548484466546L),new Date(51548488466546L),contents);
    Order v = new Order(2L,new Date(31548484466546L),new Date(31548488466546L),contents);
    Order order1 = new Order(2L,null,new Date(31548488466546L),null);
    Order order4 = new Order(2L,null,new Date(31548488466546L),null);
    Order order2 = new Order(null,new Date(31548484466546L),new Date(31548488466546L),contents);
    Order order3 = new Order(2L,null,null,null);
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
        Assertions.assertFalse(order1.equals(order2));
        Assertions.assertFalse(order1.equals(order3));
        Assertions.assertFalse(order2.equals(order3));
        Assertions.assertTrue(order1.equals(order4));
    }
}
