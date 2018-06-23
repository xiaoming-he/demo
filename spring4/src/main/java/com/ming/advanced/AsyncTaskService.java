package com.ming.advanced;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ming_he
 * @date 2018/6/23 20:25
 */
@Service
public class AsyncTaskService {

    @Async
    public void executeAyncTask(int i) {
        System.out.println("执行异步任务: " + i);
    }

    @Async
    public void executeAyncTaskPlus(int i) {
        System.out.println("执行异步任务 + 1 : " + (i + 1));
    }
}
