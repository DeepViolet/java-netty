package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * Created by liuhuiyi on 2017/5/25.
 */

/*
创建一个ServerBootstrap实例
创建一个EventLoopGroup来处理各种事件，如处理链接请求，发送接收数据等。
定义本地InetSocketAddress( port)好让Server绑定
创建childHandler来处理每一个链接请求
所有准备好之后调用ServerBootstrap.bind()方法绑定Server
*/



public class EchoServer {
    private final static int port = 8007;

    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap(); //引导辅助程序
        EventLoopGroup group = new NioEventLoopGroup(); //通过nio的方式接受连接和处理连接
        try {
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class) //设置nio类型的channel
                    .localAddress(new InetSocketAddress(port)) //设置监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { //有连接到达时会创建一个channel
                        // pipline 管理channel中的handler,在channel队列中添加一个handler来处理业务
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder", new StringDecoder());  //设置的解码格式十分关键，需要与客户端一致
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync(); //配置完成，绑定server，并通过sync同步方法阻塞直到绑定成功
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync(); //应用程序会一直等待，直到channel关闭
        } catch (Exception e) {
            e.getMessage();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
