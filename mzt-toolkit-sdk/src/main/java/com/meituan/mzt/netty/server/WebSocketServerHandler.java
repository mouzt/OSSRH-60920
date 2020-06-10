package com.meituan.mzt.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author muzhantong
 * create on 2020/5/24 12:40 下午
 */
@Slf4j
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handShaker;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if(msg instanceof FullHttpMessage){
            // 处理握手
            handlerHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
        }else if(msg instanceof WebSocketFrame){
            handlerWebSocket(channelHandlerContext, (WebSocketFrame) msg);
        }
    }

    private void handlerWebSocket(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        if(frame instanceof CloseWebSocketFrame){
            handShaker.close(channelHandlerContext.channel(), ((CloseWebSocketFrame) frame).retain());
            return;
        }
        if(frame instanceof PingWebSocketFrame){
            channelHandlerContext.channel().write(new PongWebSocketFrame());
            return;
        }
        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("receive msg,channel={},request={}"+ channelHandlerContext.channel()+"," + request);

        channelHandlerContext.channel()
                .writeAndFlush(new TextWebSocketFrame("欢迎使用Netty WebSocket服务:"+ RandomUtils.nextInt()) );


    }

    private void handlerHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) {
        if(!request.decoderResult().isSuccess() || !"websocket".equals(request.headers().get("Upgrade"))){
            sendRendHttpResponse(channelHandlerContext, request,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory =
                new WebSocketServerHandshakerFactory(null, null, false);
        handShaker = webSocketServerHandshakerFactory.newHandshaker(request);
        if(handShaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
        }else {
            handShaker.handshake(channelHandlerContext.channel(), request);
        }
    }

    private void sendRendHttpResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest request,
                                      FullHttpResponse response) {
        if(response.status().code() != 200){
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), Charset.defaultCharset());
            response.content().writeBytes(byteBuf);
            response.release();
            HttpUtil.setContentLength(response, response.content().readableBytes());
        }
        ChannelFuture f = channelHandlerContext.channel().writeAndFlush(response);
        if (!HttpUtil.isKeepAlive(request) || response.status().code() != 200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
