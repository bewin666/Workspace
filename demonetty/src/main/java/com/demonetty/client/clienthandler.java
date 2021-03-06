package com.demonetty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

@Sharable
public class clienthandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     *此方法会在连接到服务器后被调用
     * */
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        //ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        String str;

        Scanner s = new Scanner(System.in);
         do {
            System.out.print("send Message to Server in here(input 'bye!' to close):");
            str = s.nextLine();

            ctx.writeAndFlush(str);
        }while(!str.equals("bye!"));
        System.out.println("Disconnected!");
        ctx.close();
    }
    /**
     *此方法会在接收到服务器数据后调用
     * */
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        //System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
        System.out.print("Server: " + in);
    }
    /**
     *捕捉到异常
     * */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}