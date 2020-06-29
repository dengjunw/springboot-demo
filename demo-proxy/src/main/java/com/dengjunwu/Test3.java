//package com.dengjunwu;
//
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
//public class Test3 {
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
//class CglibProxy{
//    public static void main(String[] args) {
//
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(XiaoMing.class);
//        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
//            System.out.println("start...");
//            Object obj = method.invoke(o, objects);
//            System.out.println("end...");
//            return obj;
//        });
//        XiaoMing xm = (XiaoMing)enhancer.create();
//
//        System.out.println(xm.toString());
////        xm.say();
////        System.out.println(xm.getClass().getName());
//    }
//}
