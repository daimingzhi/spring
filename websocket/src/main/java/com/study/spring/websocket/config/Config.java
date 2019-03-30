package com.study.spring.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

/**
 * @author dmz
 * @date Create in 12:37 2019/3/30
 */
@Configuration
/**
 * 通过这个注解，开启使用STOMP来传输基于message broker
 * 的小心，这时的控制器支持@MessageMapping，就像@RequestMapping
 */
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {
    /**
     * 注册STOMP端点,映射指定的url
     * 端点的作用——客户端在订阅或发布消息 到目的地址前，要连接该端点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/stomp").
                /**
                 * 设置允许所有域名访问
                 */
                        setAllowedOrigins("*").
                /**
                 * 许多浏览器不支持 WebSocket 协议；
                 * SockJS 是 WebSocket 技术的一种模拟。SockJS 会 尽可能对应 WebSocket API，
                 * 但如果 WebSocket 技术 不可用的话，就会选择另外的 通信方式协议；
                 */
                        withSockJS();
    }

    /**
     * 配置消息代理
     * 如果不重载它的话，将会自动配置一个简单的内存消息代理，用它来处理以"/topic"为前缀的消息
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
        //基于RabbitMQ 的STOMP消息代理
//        registry.enableStompBrokerRelay("/queue", "/topic")
//                .setRelayHost(host)
//                .setRelayPort(port)
//                .setClientLogin(userName)
//                .setClientPasscode(password);
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return false;
    }

}
