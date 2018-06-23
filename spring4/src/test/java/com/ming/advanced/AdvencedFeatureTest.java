package com.ming.advanced;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ming_he
 * @date 2018/6/23 20:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdvancedFeatureConfig.class)
public class AdvencedFeatureTest {

    @Autowired
    AsyncTaskService asyncTaskService;

    /**
     * 主线程可能会比线程池早结束,导致没有内容输出
     */
    @Test
    public void asyncTaskTest() {
        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAyncTask(i);
            asyncTaskService.executeAyncTaskPlus(i);
        }
    }

    @Test
    public void scheduledTest() throws InterruptedException {
        Thread.sleep(1000_000);
    }
}
