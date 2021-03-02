package com.michael.proxy;

import com.michael.common.Invocation;
import com.michael.common.Url;
import com.michael.protocol.ProtocolFactory;
import com.michael.protocol.api.ProtocolClient;
import com.michael.register.RegisterFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyFactory
 * @Description 动态代理工厂
 * @Author Michael.Ng
 * @Date 2021/2/28 16:48
 **/
public class ProxyFactory   {

        public static <T>  T getProxy(final Class targetClass){
                return (T) Proxy.newProxyInstance(targetClass.getClassLoader(),new Class[]{targetClass},new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) {
                                Invocation invocation = new Invocation(targetClass.getName(),method.getName(),method.getParameterTypes(),args);
                                Url url = RegisterFactory.getRemoteService(targetClass.getName()).get(0);
                                ProtocolClient protocolClient = ProtocolFactory.getProtocolClient();
                                return protocolClient.send(url.getHost(), url.getPort(), invocation);
                        }
                });
        }
}