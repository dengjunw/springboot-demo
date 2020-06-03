package com.dengjunwu;

public class JdkProxyTest {
    public static void main(String[] args) {
        JdkDemoService demoService = JdkProxy.createProxy(new JdkDemoServiceImpl(), JdkDemoService.class);
        demoService.demo1();
    }
}
