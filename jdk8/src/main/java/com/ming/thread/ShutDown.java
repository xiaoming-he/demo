package com.ming.thread;

public class ShutDown {

    public static void main(String[] args) {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "countThread");
        countThread.start();
        SleepUtils.sleep(5);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two, "countThread");
        countThread.start();
        SleepUtils.sleep(5);
        two.cancel();
    }

    static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.interrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
