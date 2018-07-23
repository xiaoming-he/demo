package com.ming.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadPrintNumber {

    private static  int count = 0;
    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintRunner(), "t1");
        Thread t2 = new Thread(new PrintRunner(), "t2");
        t1.start();
        t2.start();
    }

    static class PrintRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    LOCK.notify();
                    if (count > 100) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count++);
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }
}
