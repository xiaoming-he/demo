package com.ming.thread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Interrupted {

    public static void main(String[] args) throws Exception{
        Thread sleepThread = new Thread(new SleepRunner(), "SleepRunner");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "BusyRunner");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupt is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupt is " + busyThread.isInterrupted());
        SleepUtils.sleep(2);
    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.sleep(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
