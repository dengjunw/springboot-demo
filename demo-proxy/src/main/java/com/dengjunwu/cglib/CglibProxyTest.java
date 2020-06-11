package com.dengjunwu.cglib;

import com.dengjunwu.jdk.JdkDemoService;
import com.dengjunwu.jdk.JdkDemoServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CglibProxyTest {
    public static void main(String[] args) {
        JdkDemoService service = CglibProxy.getProxy(new JdkDemoServiceImpl());
        service.demo1();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibProxy.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "count";
            }
        });
        CglibProxy cglibProxy = (CglibProxy)enhancer.create();
//        System.out.println(cglibProxy.sayHello());
//        System.out.println(cglibProxy.toString());
//
//        System.out.println(CglibProxyTest.class.getClassLoader());
//        System.out.println(CglibProxy.class.getSuperclass());
//
//        System.out.println(CglibProxy.class.getName());
//        System.out.println(CglibProxy.class.getSimpleName());

        Class<?>[] c1 = JdkDemoServiceImpl.class.getClasses();
        Arrays.stream(c1).forEach(one -> System.out.println(one.getName()));
//        for (Class cl : c){
//            System.out.println(cl.getName());
//        }

//        Class<?>[] c2 = CglibProxy.class.getDeclaredClasses();


        System.out.println();

    }
}
