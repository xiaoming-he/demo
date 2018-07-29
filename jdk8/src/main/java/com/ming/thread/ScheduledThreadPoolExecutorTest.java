package com.ming.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ming_he
 * @date 2018/7/28 0:07
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1);
        scheduled.schedule(()-> System.out.println("运行!"), 1, TimeUnit.SECONDS);
    }
}
