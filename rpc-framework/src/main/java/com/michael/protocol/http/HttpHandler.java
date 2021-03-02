package com.michael.protocol.http;

import com.michael.common.Invocation;
import org.apache.commons.io.IOUtils;
import com.michael.provider.api.UserService;
import com.michael.register.RegisterFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName HttpHandler
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 11:17
 **/
public class HttpHandler {

        public void handler(HttpServletRequest req, HttpServletResponse resp){

                try {
                        ServletInputStream inputStream = req.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        Invocation invocation = (Invocation) objectInputStream.readObject();

                        Class userClass = RegisterFactory.getLocalService(UserService.class.getName());
                        Method method = userClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
                        String result = (String) method.invoke(userClass.newInstance(), invocation.getParams());

                        IOUtils.write(result,resp.getOutputStream(), StandardCharsets.UTF_8);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}