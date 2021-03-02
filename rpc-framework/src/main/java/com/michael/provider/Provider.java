package com.michael.provider;

import com.michael.common.Url;
import com.michael.protocol.ProtocolFactory;
import com.michael.provider.api.UserService;
import com.michael.provider.impl.UserServiceImpl;
import com.michael.register.RegisterFactory;

/**
 * @ClassName provider
 * @Description 根据SPI思想配置Server端，由配置文件里的类名决定启动哪个server
 * @Author Michael.Ng
 * @Date 2021/2/28 11:19
 **/
public class Provider {

        public static void main(String[] args) {
                //注册本地服务
                RegisterFactory.registerLocalService(UserService.class.getName(), UserServiceImpl.class);
                //注册远程服务
                Url url = new Url(8080,"localhost");
                RegisterFactory.registerRemoteService(UserService.class.getName(),url);

                ProtocolFactory.getProtocolServer().start("localhost",8080);
        }
}