package com.ming.annotations;

import java.lang.annotation.*;

/**
 * @author ming_he
 * @date 2019/6/27 12:56 AM
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface QuartzScheduled {
    String cron();
}
