package com.michael.protocol.netty;

import com.alibaba.fastjson.JSON;
import com.michael.common.Invocation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import com.michael.provider.api.UserService;
import com.michael.register.RegisterFactory;

import java.lang.reflect.Method;

/**
 * @ClassName NettyHanlder
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 21:41
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                super.channelRead(ctx, msg);
                Invocation invocation = (Invocation) msg;

                System.out.println("receive request : "+ JSON.toJSONString(invocation));

                Class userClass = RegisterFactory.getLocalService(UserService.class.getName());
                Method method = userClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
                String result = (String) method.invoke(userClass.newInstance(), invocation.getParams());
                ByteBuf resp = Unpooled.copiedBuffer(result.getBytes());
                ctx.write(resp);
                ctx.flush();
                ctx.close();
        }
}