//package com.dengjunwu;
//
//public class Test {
//}
//
//interface Person{
//    public void say();
//}
//
//class XiaoMing implements Person{
//
//    @Override
//    public void say(){
//        System.out.println("hello~");
//    }
//}
//
//class StaticPersonProxy implements Person{
//    private XiaoMing xiaoming;
//
//    public StaticPersonProxy(XiaoMing xiaoming){
//        this.xiaoming = xiaoming;
//    }
//    @Override
//    public void say(){
//        System.out.println("proxy start...");
//        xiaoming.say();
//        System.out.println("proxy end...");
//    }
//
//    public static void main(String[] args){
//        StaticPersonProxy proxy = new StaticPersonProxy(new XiaoMing());
//        proxy.say();
//    }
//}
