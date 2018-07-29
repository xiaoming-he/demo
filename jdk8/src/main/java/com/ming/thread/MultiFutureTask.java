package com.ming.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author ming_he
 * @date 2018/7/28 12:08
 */
public class MultiFutureTask {

    private static final ConcurrentHashMap<String, Future<String>> taskCache = new ConcurrentHashMap<>();

    private static String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                FutureTask<String> futureTask = new FutureTask<>(()->taskName);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->executionTask("task"), "t1").start();
        new Thread(()->executionTask("task"), "t2").start();;
    }
}
