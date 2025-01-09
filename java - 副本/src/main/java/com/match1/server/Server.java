package com.match1.server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
public class Server {
    private static Boolean isSingleFlag = false;
    public  synchronized void start() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
            CommandHandler handler1 = new CommandHandler();

        ChannelInitializer handler = new ChannelInitializer() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(handler1);
            }
        };
        try{
            if(!isSingleFlag){
                ServerBootstrap bootstrap = new ServerBootstrap()
                        .channel(NioServerSocketChannel.class)
                        .group(boss,worker)
                        .childHandler(handler);
                ChannelFuture host = bootstrap.bind(8888).sync().addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        isSingleFlag = true;
                        System.out.println("端口绑定完成");
                    }
                });
                if(handler1.getIsExecuteFlag()){
                    host.channel().close().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            boss.shutdownGracefully();
                            worker.shutdownGracefully();
                            System.out.println("关闭服务器");
                        }
                    });
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }
}
