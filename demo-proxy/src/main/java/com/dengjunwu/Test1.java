//package com.dengjunwu;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
//public class Test1 {
//}
//
//interface Person{
//
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
//class JdkProxy implements InvocationHandler {
//
//    private XiaoMing xiaoming;
//
//    public JdkProxy(XiaoMing xiaoming){
//        this.xiaoming = xiaoming;
//    }
//
//    @Override
//    public Object invoke(Object obj, Method method, Object[] args){
//        System.out.println("jdk proxy start...");
//        Object object = null;
//        try {
//            object = method.invoke(xiaoming, args);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        System.out.println("jdk proxy end...");
//        return object;
//    }
//
//    public static void main(String[] args){
//        XiaoMing o = (XiaoMing)Proxy.newProxyInstance(XiaoMing.class.getClassLoader(), new Class[]{XiaoMing.class}, new JdkProxy(new XiaoMing()));
//        o.say();
//    }
//
//}