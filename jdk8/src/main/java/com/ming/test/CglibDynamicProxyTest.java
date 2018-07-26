package com.ming.test;


import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ming_he
 * @date 2018/7/9 23:58
 */
public class CglibDynamicProxyTest {

    static class Parent {
        public void parentSay() {
            System.out.println("parent say hello world");
        }
    }

    static class Person extends Parent{
        private String str ;

        public void say() {
            System.out.println("hello world");
        }

        private void privateStr() {
            System.out.println(str);
        }

        public void setStr() {
            str = "hello world";
        }
    }

    static class CglibProxy implements MethodInterceptor {

        Object target;

        public Object getProxy(Object target) {
            this.target = target;
            final Enhancer enhancer = new Enhancer();
            enhancer.setUseFactory(false);
            enhancer.setSuperclass(target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("say before");
            methodProxy.invokeSuper(o, objects);
            System.out.println("say after");
            return null;
        }
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "G:\\gitRepository\\demo");
        CglibProxy cglibProxy = new CglibProxy();
        Person person = new Person();
        person.setStr();
        Person proxyPerson = (Person) cglibProxy.getProxy(person);
        proxyPerson.privateStr();
    }
}
