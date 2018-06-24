package com.ming.web.servlet3;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author ming_he
 * @date 2018/6/24 16:02
 */
@WebListener
public class HelloListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("HelloListener contextInitialized()");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("HelloListener contextDestroyed()");
    }
}
