package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DocumentTest {
    @Test
    public void testEqualsWhenIdNull() {
        //given
        Order order = new Order(1L, LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null);
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
        Order order = new Order(1L,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null);
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
        Order order = new Order(1L,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null);
        Document document = new Document(null, order, "Some content");

        //when
        String output = document.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}
