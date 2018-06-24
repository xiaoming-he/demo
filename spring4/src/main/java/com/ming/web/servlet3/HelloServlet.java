package com.ming.web.servlet3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ming_he
 * @date 2018/6/24 15:24
 */
@WebServlet(name = "helloServlet", urlPatterns = "/helloServlet",
        initParams = {@WebInitParam(name="name", value = "hello")})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = this.getInitParameter("name");
        resp.getWriter().print(name);
    }


}
