package com.ming.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

/**
 * @author ming_he
 * @date 2018/7/28 11:55
 */
public class FutureTest {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> future = new FutureTask<>(() ->
                IntStream.range(0, 10).limit(10).sum()
        );
        new Thread(future).start();

        System.out.println(future.get());
        System.out.println(future.get());
    }
}
