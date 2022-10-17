package com.ku.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    @Test
    public void testEqualsWhenIdNull() {
        //given
        Service first = new Service(null,"sell",355L,"sell something");
        Service second = new Service(null,"help",355L,"help to somebody");

        //when
        boolean isEqual = first.equals(second);

        //then
        Assertions.assertFalse(isEqual);
    }

    @Test
    public void testHashCodeWhenIdNull() {
        //given
        Service first = new Service(null,"sell",355L,"sell something");
        Service second = new Service(null,"help",355L,"help to somebody");

        //when
        boolean isHashCodeEqual = first.hashCode()==second.hashCode();

        //then
        Assertions.assertFalse(isHashCodeEqual);
    }

    @Test
    public void testToStringWithCollections() {
        //given
        Service service = new Service(null,"sell",355L,"sell something");

        //when
        String output = service.toString();

        //then
        System.out.println(output);
        Assertions.assertTrue(output.contains("id=null"));
    }
}
