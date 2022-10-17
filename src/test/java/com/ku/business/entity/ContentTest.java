package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentTest {
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
        Content first = new Content(null,255L,service,orders);
        Content second = new Content(null,384L,service,null);

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
        Content first = new Content(null,255L,service,orders);
        Content second = new Content(null,384L,service,null);

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testHashCodeCollisionForDifferentObjects() {
        //given
        List<Order> orders = new ArrayList<>();
        Content first = new Content(1L,255L,null,orders);
        Content second = new Content(1L,255L,null,null);

        //when
        boolean isHashCodeEqual = first.hashCode() == second.hashCode();
        boolean isObjectsEqual = first.equals(second);

        //then
        Assertions.assertTrue(isHashCodeEqual);
        Assertions.assertFalse(isObjectsEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Service service = new Service(1L,"sell",355L,"sell something");
        Order firstOrder = new Order(1L,new Date(31548484466546L),new Date(31548994466546L), null);
        Order secondOrder = new Order(2L,new Date(31543542466546L),null, null);
        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);
        Content content = new Content(null,255L,service,orders);

        //when
        String output = content.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}

