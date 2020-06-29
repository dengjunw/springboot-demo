package com.dengjunwu.jdk;

public class JdkProxyTest {
    public static void main(String[] args) {
        JdkDemoService demoService = JdkProxy.createProxy(new JdkDemoServiceImpl(), JdkDemoService.class);
//        demoService.demo1();
        System.out.println(demoService);
    }
}
