package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    @Test
    public void testEqualsWhenIdNull() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(null, LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null);
        Order secondOrder = new Order(null,LocalDateTime.of(2022, 11, 14, 9, 25),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(null);
        Content firstContent = new Content(null,255L,service,orders);
        Content secondContent = new Content(null,384L,service,null);
        List<Content> contents = new ArrayList<>();
        contents.add(firstContent);
        contents.add(secondContent);
        Order first = new Order(null,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5),contents);
        Order second = new Order(null,null,LocalDateTime.of(2022, 11, 14, 9, 25),null);

        //when
        boolean isEqual = first.equals(second);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testHashCodeWhenIdNull() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(null, LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null);
        Order secondOrder = new Order(null,LocalDateTime.of(2022, 11, 14, 9, 25),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(null);
        Content firstContent = new Content(null,255L,service,orders);
        Content secondContent = new Content(null,384L,service,null);
        List<Content> contents = new ArrayList<>();
        contents.add(firstContent);
        contents.add(secondContent);
        Order first = new Order(null,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5),contents);
        Order second = new Order(null,null,LocalDateTime.of(2022, 11, 14, 9, 25),null);

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(null, LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5), null);
        Order secondOrder = new Order(null,LocalDateTime.of(2022, 11, 14, 9, 25),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(null);
        Content firstContent = new Content(null,255L,service,orders);
        Content secondContent = new Content(null,384L,service,null);
        List<Content> contents = new ArrayList<>();
        contents.add(firstContent);
        contents.add(secondContent);
        Order order = new Order(null,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5),contents);

        //when
        String output = order.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }

    @Test
    public void testHashCodeCollisionForDifferentObjects() {
        //given
        List<Content> contents = new ArrayList<>();
        Order first = new Order(null,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5),contents);
        Order second = new Order(null,LocalDateTime.of(2022, 9, 19, 14, 5),LocalDateTime.of(2022, 9, 29, 14, 5),null);

        //when
        boolean isHashCodeEqual = first.hashCode() == second.hashCode();
        boolean isObjectsEqual = first.equals(second);

        //then
        Assertions.assertTrue(isHashCodeEqual);
        Assertions.assertFalse(isObjectsEqual);
    }
}
