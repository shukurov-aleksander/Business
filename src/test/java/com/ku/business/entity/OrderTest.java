package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTest {

    @Test
    public void testEqualsWhenIdNull() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Order secondOrder = new Order(null,new Date(31543542466546L),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(null);
        Content firstContent = new Content(null,255L,service,orders);
        Content secondContent = new Content(null,384L,service,null);
        List<Content> contents = new ArrayList<>();
        contents.add(firstContent);
        contents.add(secondContent);
        Order first = new Order(null,new Date(31524488466546L),new Date(31548488466546L),contents);
        Order second = new Order(null,null,new Date(31548488466546L),null);

        //when
        boolean isEqual = first.equals(second);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testHashCodeWhenIdNull() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Order secondOrder = new Order(null,new Date(31543542466546L),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(null);
        Content firstContent = new Content(null,255L,service,orders);
        Content secondContent = new Content(null,384L,service,null);
        List<Content> contents = new ArrayList<>();
        contents.add(firstContent);
        contents.add(secondContent);
        Order first = new Order(null,new Date(31524488466546L),new Date(31548488466546L),contents);
        Order second = new Order(null,null,new Date(31548488466546L),null);

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Order secondOrder = new Order(null,new Date(31543542466546L),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        orders.add(null);
        Content firstContent = new Content(null,255L,service,orders);
        Content secondContent = new Content(null,384L,service,null);
        List<Content> contents = new ArrayList<>();
        contents.add(firstContent);
        contents.add(secondContent);
        Order order = new Order(null,new Date(31524488466546L),new Date(31548488466546L),contents);

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
        Order first = new Order(null,new Date(31548484466546L),new Date(31548488466546L),contents);
        Order second = new Order(null,new Date(31548484466546L),new Date(31548488466546L),null);

        //when
        boolean isHashCodeEqual = first.hashCode() == second.hashCode();
        boolean isObjectsEqual = first.equals(second);

        //then
        Assertions.assertTrue(isHashCodeEqual);
        Assertions.assertFalse(isObjectsEqual);
    }
}
