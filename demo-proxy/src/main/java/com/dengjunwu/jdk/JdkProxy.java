package com.dengjunwu.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start...");
        Object o =  method.invoke(this.target, args);
        System.out.println("end...");
        return o;
    }

    /**
     * 创建任意类的 代理类
     * @param target            代理类
     * @param targetInterface   代理类接口
     * @param <T>               类型
     * @return
     */
    public static <T> T createProxy(Object target, Class<T> targetInterface){
        if (!targetInterface.isInterface()){
            throw new IllegalStateException("targetInterface 必须是接口类型");
        }
        if (!targetInterface.isAssignableFrom(target.getClass())){
            throw new IllegalStateException("target必须是targetInterface的实现类");
        }
        T o = (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{targetInterface}, new JdkProxy(target));
        return o;
    }
}
