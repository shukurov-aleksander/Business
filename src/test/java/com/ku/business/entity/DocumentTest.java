package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DocumentTest {
    List<Content> contents = new ArrayList<>();
    Order order = new Order();
    Document x = new Document(1L, order, "Some text");
    Document y = new Document(1L, order, "Some text");
    Document z = new Document(1L, order, "Some text");
    Document v = new Document(2L, order, "Some different text");
    Document document1 = new Document(2L, null, "Some different text");
    Document document4 = new Document(2L, null, "Some different text");
    Document document2 = new Document(null, order, "Some different text");
    Document document3 = new Document(2L, null, "null");

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
        Assertions.assertFalse(document1.equals(document2));
        Assertions.assertFalse(document1.equals(document3));
        Assertions.assertFalse(document2.equals(document3));
        Assertions.assertTrue(document1.equals(document4));
    }
}
