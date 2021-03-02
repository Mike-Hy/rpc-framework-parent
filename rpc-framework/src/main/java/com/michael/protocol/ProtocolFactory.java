package com.michael.protocol;

import com.michael.protocol.api.ProtocolServer;
import com.michael.protocol.netty.NettyServer;
import com.michael.protocol.netty.NettyClient;
import com.michael.protocol.api.ProtocolClient;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * @ClassName ProtocolFactory
 * @Description 网络通信协议工厂
 * @Author Michael.Ng
 * @Date 2021/3/1 10:26
 **/
public class ProtocolFactory {

        public static ProtocolClient getProtocolClient(){
                ServiceLoader<ProtocolClient> serviceLoader = ServiceLoader.load(ProtocolClient.class);
                final Optional<ProtocolClient> protocol = StreamSupport.stream(serviceLoader.spliterator(), false)
                        .findFirst();
                return protocol.orElse(new NettyClient());
        }

        public static ProtocolServer getProtocolServer(){
                ServiceLoader<ProtocolServer> serviceLoader = ServiceLoader.load(ProtocolServer.class);
                final Optional<ProtocolServer> protocol = StreamSupport.stream(serviceLoader.spliterator(), false)
                        .findFirst();
                return protocol.orElse(new NettyServer());
        }

}