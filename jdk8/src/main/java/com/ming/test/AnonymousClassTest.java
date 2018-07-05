package com.ming.test;

/**
 * @author ming_he
 * @date 2018/7/5 22:16
 */
public class AnonymousClassTest {

    interface ObjectFactory {
        void invoke();
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        String str = "Hello World";
        ObjectFactory factory = new ObjectFactory() {
            @Override
            public void invoke() {
                print(str);
            }
        };
        factory.invoke();
    }
}
