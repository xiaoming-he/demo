package com.ming.thread;

public class MultiInterface {

    interface InterfaceA {
        void say();
    }

    interface InterfaceB {
        void say();
    }

    class ImplA implements InterfaceA, InterfaceB {

        @Override
        public void say() {
            System.out.println(super.getClass());
        }
    }

    public static void main(String[] args) {
        ImplA implA = new MultiInterface().new ImplA();
        implA.say();
    }
}
