package com.ku.business.annotation;

@ClassAnnotation
public class Animal {
    private long age;
    @FieldAnnotation
    private String name;
    @MethodAnnotation
    public void run() {

    }
}
