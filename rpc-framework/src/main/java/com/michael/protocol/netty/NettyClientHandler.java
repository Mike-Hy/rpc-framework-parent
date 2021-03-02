package com.michael.protocol.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName NettyClientHandler
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 21:55
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

        private String response;

        public String getResponse() {
                return response;
        }

        /**
         * 接收服务端发送的信息
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;
                byte[] req = new byte[buf.readableBytes()];
                buf.readBytes(req);
                this.response =new String(req, StandardCharsets.UTF_8);
        }

}