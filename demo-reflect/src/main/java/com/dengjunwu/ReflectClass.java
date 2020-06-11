package com.dengjunwu;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectClass {
    private static final String TAG = "com.dengjunwu.ReflectClass";

    /**
     * 反射创建实体
     */
    public static void reflectNewInstance(){
        try {
            Class<?> clazz = Class.forName("com.dengjunwu.Book");
            Book book = (Book) clazz.newInstance();
            book.setName("生命之光");
            book.setAuthor("理查德克莱德曼");
            System.out.println(book.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射私有构造方法
     */
    public static void reflectPrivateConstructor(){
        try {
            Class<?> clazz = Class.forName("com.dengjunwu.Book");
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, String.class);
            declaredConstructor.setAccessible(true);
            Book book = (Book) declaredConstructor.newInstance("生命之光", "理查德克莱德曼");
            System.out.println(book.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射私有属性
     */
    public static void reflectPrivateField(){
        try {
            Class<?> clazz = Class.forName("com.dengjunwu.Book");
//            Object obj = clazz.newInstance();
            Field field = clazz.getDeclaredField("TAG");
            field.setAccessible(true);
            String value = (String) field.get(clazz);
            System.out.println(value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateMethod(){
        try {
            Class<?> clazz = Class.forName("com.dengjunwu.Book");
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("declaredMethod", Integer.class);
            method.setAccessible(true);
            Object value = method.invoke(obj, 0);
            System.out.println(value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        reflectNewInstance();
//        reflectPrivateConstructor();
//        reflectPrivateField();
        reflectPrivateMethod();
    }
}
