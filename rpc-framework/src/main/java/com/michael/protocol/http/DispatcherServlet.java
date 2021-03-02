package com.michael.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HttpServlet
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 10:25
 **/
public class DispatcherServlet extends javax.servlet.http.HttpServlet {


        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                new HttpHandler().handler(req,resp);
        }
}