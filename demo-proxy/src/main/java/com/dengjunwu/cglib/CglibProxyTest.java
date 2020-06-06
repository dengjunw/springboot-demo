package com.dengjunwu.cglib;

import com.dengjunwu.jdk.JdkDemoService;
import com.dengjunwu.jdk.JdkDemoServiceImpl;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CglibProxyTest {
    public static void main(String[] args) {
        JdkDemoService service = CglibProxy.getProxy(new JdkDemoServiceImpl());
        service.demo1();
    }
}
