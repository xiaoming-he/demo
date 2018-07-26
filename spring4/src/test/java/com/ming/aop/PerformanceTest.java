package com.ming.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PerformConfig.class)
public class PerformanceTest {

    @Autowired
    private Performance performance;

    @Test
    public void testPerform() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "G:\\gitRepository\\demo");
        performance.perform();
    }

    class DeclaredMethods {
        public void test1() {

        }

        private void test2() {

        }
    }

    @Test
    public void testDeclaredMethods() {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(DeclaredMethods.class);
        Arrays.stream(methods).forEach(System.out::println);
    }
}
