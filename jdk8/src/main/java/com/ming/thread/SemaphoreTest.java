package com.ming.thread;

import java.util.concurrent.*;

/**
 * @author ming_he
 * @date 2018/7/28 17:20
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private final static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(()->{
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName()+"执行!");
                    SleepUtils.sleep(20000);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            SleepUtils.sleep(1000);
        }
    }
}
