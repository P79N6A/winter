package com.panda.nio.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public void bind(int port) {
        //创建EventLoopGroup
        //eventLoopGroup为Netty的reactor线程池，它实际上就是eventLoop的容器，而eventLoop为Netty的核心抽象类，它的主要职责是处理所有注册到本线程多路复用器selector上的channel。
        EventLoopGroup bossGroup = new NioEventLoopGroup();        //创建BOSS线程组 用于服务端接受客户端的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();      //创建WORK线程组 用于进行SocketChannel的网络读写

        try {
            //创建ServerBootStrap实例
            //serverBootstrap用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
            ServerBootstrap b = new ServerBootstrap();
            //绑定Reactor线程池
            //当一个连接到达时，Netty就会注册一个channel，然后从eventLoopGroup中分配一个eventLoop绑定到这个channel上，在该Channel的整个生命周期中都是有这个绑定的eventLoop来服务的。
            b.group(bossGroup, workerGroup)
                    //设置channel类型
                    .channel(NioServerSocketChannel.class)
                    //用option()方法设置channel参数，作为服务端，主要是设置TCP的backlog参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //设置的handler是服务端nioServerSocketChannel的
                    .handler(new LoggingServerHandler())
                    //设置的handler是属于每一个新建的nioSocketChannel的
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            //do something
                        }
                    });

            // 绑定端口，同步等待成功
            ChannelFuture future = b.bind(port).sync();
            // 等待服务端监听端口关闭
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅地关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class LoggingServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("loggin-channelActive");
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("loggin-channelRegistered");
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("loggin-handlerAdded");
        }
    }

    public static void main(String[] args) {
        new NettyServer().bind(8899);
    }
}
