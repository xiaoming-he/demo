package com.ming.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author ming_he
 * @date 2018/7/25 22:58
 */
public class ThreadPoolExecutorTest {

    private final static BlockingQueue queue = new LinkedBlockingQueue(10);
    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MINUTES, queue);
    private final static ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "执行!");
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        scheduleExecutor.scheduleAtFixedRate(()->
            System.out.println("poolSize:" + executor.getPoolSize() + " queueSize: " + queue.size()),
                0, 1, TimeUnit.SECONDS);
    }
}
