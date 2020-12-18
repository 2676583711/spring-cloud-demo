package cn.zhou.codedemo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LoggingHandler;
import org.apache.tomcat.util.net.NioChannel;

import javax.xml.ws.spi.http.HttpHandler;

/*
(1)、 初始化用于Acceptor的主"线程池"以及用于I/O工作的从"线程池"；
(2)、 初始化ServerBootstrap实例， 此实例是netty服务端应用开发的入口，也是本篇介绍的重点， 下面我们会深入分析；
(3)、 通过ServerBootstrap的group方法，设置（1）中初始化的主从"线程池"；
(4)、 指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel；
(5)、 设置ServerSocketChannel的处理器（此处不详述，后面的系列会进行深入分析）
(6)、 设置子通道也就是SocketChannel的处理器， 其内部是实际业务开发的"主战场"（此处不详述，后面的系列会进行深入分析）
(7)、 配置ServerSocketChannel的选项
(8)、 配置子通道也就是SocketChannel的选项
(9)、 绑定并侦听某个端口

https://blog.csdn.net/woaixiaopangniu521/article/details/70256018
 */
public class NettyDemo {
    private static int port = 10086;

    public static void main(String[] args) {
//        NioEventLoop eventExecutors1 = new NioEventLoop();

        NioEventLoopGroup parent = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /*
        服务端会绑定一个端口,然后服务端监听这个端口,如果有客户端的请求,服务器会给响应,并且会给客户端发送数据.
        对于ServerBootstrap来说,处理socket连接的线程就是Boos线程,就是ServerBootstrap的group方法的第一个参数.
        服务端接受了socket连接求后，会产生一个channel（一个打开的socket对应一个打开的channel），
        并把这个channel交给Worker线程来处理,就是ServerBootstrap的group方法的第二个参数，boss线程则继续处理socket的请求。
        如果客户端数目不是很多的情况,Boos线程和Worker线程可以是同一个。

        ServerBootstrap有一个handler,
        但是还有一个childHandler。handler在ServerBootstrap初始化时就会执行，
        而childHandler会在客户端成功connect后才执行，这是两者的区别。

        ServerBootstrap有一个option,
        但是还有一个childOption。option是给Boos线程设置的,
        childOption是给Worker线程设置的。设置的参数的值同Bootstrap。
         */
        serverBootstrap.group(parent, child)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler())
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("initChannel ch:" + socketChannel);

                        socketChannel.pipeline()
                                .addLast("decoder", new HttpRequestDecoder())   // 1
                                .addLast("encoder", new HttpResponseEncoder())  // 2
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3
                                .addLast("handler", new ServerHandler());        // 4

                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture f = null;
        try {
            f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parent.shutdownGracefully();
            child.shutdownGracefully();
        }

    }

}
