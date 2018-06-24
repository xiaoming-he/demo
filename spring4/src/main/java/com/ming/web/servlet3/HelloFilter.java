package com.ming.web.servlet3;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ming_he
 * @date 2018/6/24 15:42
 */
@WebFilter(filterName = "helloFilter", urlPatterns = "/*")
public class HelloFilter extends HttpFilter {
    @Override
    public void init() throws ServletException {
        System.out.println("helloFilter init()");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("helloFilter doFilter()");
        chain.doFilter(req,  res);
    }

    @Override
    public void destroy() {
        System.out.println(" helloFilter destroy()");
    }
}
