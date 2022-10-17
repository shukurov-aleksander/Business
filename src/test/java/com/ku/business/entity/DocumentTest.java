package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DocumentTest {
    @Test
    public void testEqualsWhenIdNull() {
        //given
        Order order = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Document first = new Document(null, order, "Some content");
        Document second = new Document(null, null, "Some other content");

        //when
        boolean isEqual = first.equals(second);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testHashCodeWhenIdNull() {
        //given
        Order order = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Document first = new Document(null, order, "Some content");
        Document second = new Document(null, null, "Some other content");

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Order order = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Document document = new Document(null, order, "Some content");

        //when
        String output = document.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}
