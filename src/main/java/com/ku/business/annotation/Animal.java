package com.ku.business.annotation;

@MyAnnotation.classAnnotation
public class Animal {
    @MyAnnotation.fieldAnnotation(age = 4)
public int size;
    @MyAnnotation.methodAnnotation
    public void run() {
        System.out.println("run");
    }
}
