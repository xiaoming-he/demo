package com.ming.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotify {
    private static Object lock = new Object();
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "wait");
        Thread notifyThread = new Thread(new Notify(), "notify");
        waitThread.start();
        SleepUtils.sleep(1);
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(Thread.currentThread().getName()
                            + " flag is true "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()
                        + " flag is false "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                flag = false;
                lock.notifyAll();
                SleepUtils.sleep(50);
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock again "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.sleep(5);
            }
        }
    }

}
