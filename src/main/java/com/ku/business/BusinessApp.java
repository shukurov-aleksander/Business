package com.ku.business;

import com.ku.business.annotation.Animal;
import com.ku.business.annotation.Bird;
import com.ku.business.annotation.Car;
import com.ku.business.annotation.Cat;
import com.ku.business.annotation.Dog;
import com.ku.business.annotation.House;
import com.ku.business.annotation.Human;
import com.ku.business.annotation.Pen;
import com.ku.business.annotation.Table;
import com.ku.business.annotation.Telephone;
import com.ku.business.annotation.ClassAnnotation;
import com.ku.business.annotation.MethodAnnotation;
import com.ku.business.annotation.FieldAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BusinessApp {
    public static void main(String[] args) {
        List<Object> objects = List.of(new Animal(), new Bird(), new Car(), new Cat(), new Dog(), new House(), new Human(), new Pen(), new Table(), new Telephone());

        for (Object o: objects)
            {
            Class<?> aClass =  o.getClass();
            if (aClass.isAnnotationPresent(ClassAnnotation.class)) {
                System.out.println("Class '" + aClass.getSimpleName() + "' has class annotation '" + ClassAnnotation.class.getSimpleName() + "'.");
            }
        }

        for (Object o: objects)
        {
            Field[] fields =  o.getClass().getDeclaredFields();
            for (Field field: fields)
            {
                if (field.isAnnotationPresent(FieldAnnotation.class)) {
                    System.out.println("Class '" + o.getClass().getSimpleName() + "' has field annotation '" + FieldAnnotation.class.getSimpleName() + "'.");
                    break;
                }
            }
        }

        for (Object o: objects)
        {
            Method[] methods =  o.getClass().getDeclaredMethods();
            for (Method method: methods)
            {
                if (method.isAnnotationPresent(MethodAnnotation.class)) {
                    System.out.println("Class '" + o.getClass().getSimpleName() + "' has method annotation '" + MethodAnnotation.class.getSimpleName() + "'.");
                    break;
                }
            }
        }

        for (Object o: objects)
        {
            Class<?> aClass =  o.getClass();
            Class<?> superClass =  aClass.getSuperclass();
            Field[] fields =  superClass.getDeclaredFields();
            for (Field field: fields)
            {
                if (field.isAnnotationPresent(FieldAnnotation.class)) {
                    System.out.println("Class '" + aClass.getSimpleName() + "' has field annotation '" + FieldAnnotation.class.getSimpleName() + "'.");
                    break;
                }
            }
        }
    }
}
