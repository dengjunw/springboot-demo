package com.dengjunw.config;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CostTimeProxy implements MethodInterceptor {

    private Object target;

    public CostTimeProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, objects);
        long entTime = System.nanoTime();
        System.out.println(method + ", 耗时（纳秒）：" + (entTime - startTime));
        return result;
    }

    /**
     * 创建任意类的代理对象
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T createProxy(T target){
        CostTimeProxy costTimeProxy = new CostTimeProxy(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(costTimeProxy);
        enhancer.setSuperclass(target.getClass());
        return (T)enhancer.create();
    }
}
