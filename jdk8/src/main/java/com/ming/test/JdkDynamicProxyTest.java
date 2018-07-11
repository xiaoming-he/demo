package com.ming.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ming_he
 * @date 2018/7/8 20:55
 */
public class JdkDynamicProxyTest {

    interface IPerson {
        void say() ;
    }

    static class Person implements IPerson {
        @Override
        public void say() {
            System.out.println("hello world");
        }
    }

    static class JdkProxy implements InvocationHandler {

        //目标对象
        private Object target;

        //得到代理对象
        public Object getProxy(Object target) {
            this.target = target;
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("say before");
            method.invoke(target, args);
            System.out.println("say after");
            return null;
        }
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        JdkProxy jdkProxy = new JdkProxy();
        IPerson person = (IPerson) jdkProxy.getProxy(new Person());
        person.say();
    }
}

