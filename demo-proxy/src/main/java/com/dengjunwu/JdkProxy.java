package com.dengjunwu;

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
        return method.invoke(this.target, args);
    }

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
