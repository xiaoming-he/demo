package com.ming.annotations;

import com.ming.quartz.QuartzScheduledRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ming_he
 * @date 2019/7/5 18:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(QuartzScheduledRegistrar.class)
public @interface EnableQuartzScheduled {

    /**
     * 扫描的基础包
     * @return
     */
    String basePackage() default "";
}
