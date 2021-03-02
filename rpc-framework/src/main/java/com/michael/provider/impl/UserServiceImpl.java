package com.michael.provider.impl;

import com.michael.provider.api.UserService;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 10:04
 **/
public class UserServiceImpl implements UserService {

        public String getUserInfo(String name) {
                return "userName  = "+ name;
        }
}