package com.michael.protocol.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import com.michael.protocol.api.ProtocolServer;

/**
 * @ClassName NettyServer
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 21:35
 **/
public class NettyServer implements ProtocolServer {


        public void start(String hostName,int port){
                // 启动服务
                try {
                        EventLoopGroup bossGroup = new NioEventLoopGroup();
                        EventLoopGroup workerGroup = new NioEventLoopGroup();

                        ServerBootstrap bootstrap = new ServerBootstrap();
                        bootstrap.group(bossGroup, workerGroup)
                                .channel(NioServerSocketChannel.class)
                                .childHandler(new ChannelInitializer<Channel>() {
                                        @Override
                                        protected void initChannel(Channel channel) throws Exception {
                                                ChannelPipeline pipeline = channel.pipeline();
                                                pipeline.addLast(new ObjectEncoder());
                                                pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(NettyServer.class.getClassLoader())));
                                                pipeline.addLast(new NettyServerHandler());
                                        }
                                }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

                        ChannelFuture future = bootstrap.bind(hostName, port).sync();
                        System.out.println("netty server is started...");
                        future.channel().closeFuture().sync();
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}