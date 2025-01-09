package com.match1.server;

import com.match1.mapper.Mapper;
import com.match1.pojo.CacheConfig;
import com.match1.pojo.GlobalStorage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Data
@Component
@Slf4j
@ChannelHandler.Sharable
public class CommandHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private Mapper mapper;
    private static HashMap<String,Object> test = new HashMap<>();
    private static List<Object> MESSAGE_RESPONSE_LIST = new ArrayList<>();
    private static Boolean isExecuteFlag = false;
    private static ChannelId channelId;
    private String message;
    private static String currentUUID;
    public static void channelWrite(ChannelId channelId, Object msg) throws Exception {
        Channel channel = ChannelMap.getChannelMap().get(channelId);
        if (channel == null) {
            log.info("通道:{},不存在", channelId);
            return;
        }
        if (msg == null || msg == "") {
            log.info("服务端响应空的消息");
            return;
        }
        //将客户端的信息直接返回写入ctx
        System.out.println(msg.toString());
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(msg.toString().getBytes());
        ChannelFuture write = channel.writeAndFlush(buf);
        write.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("数据传输完毕");
                Thread.sleep(500);
            }
        });
    }
    public static HashMap<String,Object> getTest(){
        return test;
    }
    public static Boolean getIsExecuteFlag() {
        return isExecuteFlag;
    }

    public static ChannelId getChannelId() {
        return channelId;
    }

    public static String getCurrentUUID() {
        return currentUUID;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        channelId = id;
        ChannelMap.addChannel(id, ctx.channel());
        log.info("客户端:{},客户端IP[IP:{}]",id, ctx.channel());
        System.out.println("连接成功");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("开始从硬件读取内容");
        System.out.println("来自硬件的消息是:" + msg);
        test.put("msg",msg);
        isExecuteFlag = true;
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
    public String getResult(String msg){
        String result = msg;
        return msg;
    }
    public static Object getLast() {
        while(true){
            if(isExecuteFlag){
                System.out.println("返回数据");
                isExecuteFlag = false;
                return MESSAGE_RESPONSE_LIST.get(MESSAGE_RESPONSE_LIST.size() - 1);
            }
        }
    }


}

