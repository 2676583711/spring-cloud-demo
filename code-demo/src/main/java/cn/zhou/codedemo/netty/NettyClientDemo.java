package cn.zhou.codedemo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 客户端的开发步骤和服务端都差不多：
(1)、 初始化用于连接及I/O工作的"线程池"；
(2)、 初始化Bootstrap实例， 此实例是netty客户端应用开发的入口，也是本篇介绍的重点， 下面我们会深入分析；
(3)、 通过Bootstrap的group方法，设置（1）中初始化的"线程池"；
(4)、 指定通道channel的类型，由于是客户端，故而是NioSocketChannel；
(5)、 设置SocketChannel的选项（此处不详述，后面的系列会进行深入分析）；
(6)、 设置SocketChannel的处理器， 其内部是实际业务开发的"主战场"（此处不详述，后面的系列会进行深入分析）；
(7)、 连接指定的服务地址；
 */
public class NettyClientDemo {
    private static String host = "";
    private static int port = 10086;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // (1)
        try {
//            System.err.println("");
            Bootstrap b = new Bootstrap(); // (2)
            b.group(workerGroup); // (3)
            b.channel(NioSocketChannel.class); // (4)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (5)
            b.handler(new ChannelInitializer<SocketChannel>() { // (6)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast((ChannelHandler) new TimeClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (7)
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}

