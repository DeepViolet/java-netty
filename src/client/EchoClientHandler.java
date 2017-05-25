package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by liuhuiyi on 2017/5/25.
 */
class EchoClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     *此方法会在连接到服务器后被调用
     * */
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Netty rocks!");
//        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     * 接收到服务器数据时调用
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Client received: " + msg);
    }

    /**
     *捕捉到异常
     * */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}