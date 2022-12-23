package com.ku.business;

import com.ku.business.annotation.MyAnnotation;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BusinessApp {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.ku.business.annotation");
        Set<Class<?>> setClassAnnotations = reflections.getTypesAnnotatedWith(MyAnnotation.classAnnotation.class);
        Set<Class<?>> setFiledAnnotations = reflections.getTypesAnnotatedWith(MyAnnotation.fieldAnnotation.class);
        Set<Class<?>> setMethodAnnotations = reflections.getTypesAnnotatedWith(MyAnnotation.methodAnnotation.class);

        List<String> collectClassAnnotations = setClassAnnotations.stream()
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
        List<String> collectFieldAnnotations = setFiledAnnotations.stream()
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
        List<String> collectMethodAnnotations = setMethodAnnotations.stream()
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());

        for (String c: collectClassAnnotations
        ) {
            System.out.println(c.toString());
        }
        System.out.println("------------------------------------------");
        for (String c: collectFieldAnnotations
        ) {
            System.out.println(c.toString());
        }
        System.out.println("------------------------------------------");
        for (String c: collectMethodAnnotations
        ) {
            System.out.println(c.toString());
        }
    }
}
