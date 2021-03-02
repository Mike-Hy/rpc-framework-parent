package com.michael.consumer;

import com.michael.provider.api.UserService;
import com.michael.proxy.ProxyFactory;

/**
 * @ClassName consumer
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 12:13
 **/
public class Consumer {

        public static void main(String[] args) {
                UserService userService = ProxyFactory.getProxy(UserService.class);
                System.out.println(userService.getUserInfo("Jordan"));
        }
}