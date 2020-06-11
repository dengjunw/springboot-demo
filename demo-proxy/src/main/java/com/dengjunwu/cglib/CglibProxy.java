package com.dengjunwu.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(){}

    public CglibProxy(Object target){
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return method.invoke(this.target, objects);
    }

    public String sayHello(){
        return "hello";
    }

    public static <T> T getProxy(T target){
        CglibProxy proxy = new CglibProxy(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(proxy);
        enhancer.setSuperclass(target.getClass());
        return (T) enhancer.create();
    }
}
