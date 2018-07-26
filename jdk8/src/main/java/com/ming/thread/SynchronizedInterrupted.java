package com.ming.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedInterrupted {
    public static void main(String[] args) {
        Thread t1 = new Thread(new LockRunner(), "t1");
        Thread t2 = new Thread(new LockRunner(), "t2");
        t1.start();
        t2.start();
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }

    private static final Object LOCK = new Object();
    static class SynchronizedRunner implements Runnable {

        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + "获得锁!");
                SleepUtils.sleep(5000);
            }
        }
    }

    private static final Lock lock = new ReentrantLock();
    static class LockRunner implements Runnable {

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println(Thread.currentThread().getName() + "获得锁!");
                    SleepUtils.sleep(5000);
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
