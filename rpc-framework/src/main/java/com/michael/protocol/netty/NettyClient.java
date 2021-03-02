package com.michael.protocol.netty;

import com.alibaba.fastjson.JSON;
import com.michael.common.Invocation;
import com.michael.protocol.api.ProtocolClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 21:50
 **/
public class NettyClient implements ProtocolClient {

        public String  send(String hostName, int port, final Invocation invocation){
                final NettyClientHandler nettyClientHandler = new NettyClientHandler();
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                        Bootstrap bootstrap = new Bootstrap();
                        bootstrap.group(group)
                                .channel(NioSocketChannel.class)
                                .option(ChannelOption.TCP_NODELAY, true)
                                .handler(new ChannelInitializer<Channel>() {
                                        @Override
                                        protected void initChannel(Channel channel) throws Exception {
                                                ChannelPipeline pipeline = channel.pipeline();
                                               // pipeline.addLast( new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(ConsumerProxy.class.getClassLoader())));
                                                pipeline.addLast( new ObjectEncoder());
                                                pipeline.addLast(nettyClientHandler);
                                        }
                                });
                        ChannelFuture future = bootstrap.connect(hostName, port).sync();

                        Channel channel = future.channel();
                        channel.writeAndFlush(invocation);
                        System.out.println("send request ："+ JSON.toJSONString(invocation));
                        channel.closeFuture().sync();
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                } finally {
                        group.shutdownGracefully();
                }
                return nettyClientHandler.getResponse();
        }
}