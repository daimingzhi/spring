package com.study.spring.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author dmz
 * @date Create in 15:45 2019/4/6
 * @desc
 */
public class ConsumerApplication {

    public static final String QUEUE_NAME = "queue";

    public static void main(String[] args) {
        // 建立连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 设置虚拟机，一个虚拟机相当于一个独立的mq
        factory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            // 建立通道
            channel = connection.createChannel();
            // 声明队列
            /**
             * 这一步声明的队列必须跟我们在生产者中声明的队列一样，参数也必须一样，否则会报错
             * 对队列的声明是幂等的，之所以在这里再次申明是为了方便启动服务
             * 有时候，生产者还没启动，我们消费者已经启动了
             */
            channel.queueDeclare(QUEUE_NAME, true
                    , false, false, null);
            // 监听队列
            /**
             * String queue,
             * boolean autoAck,
             * String consumerTag,
             * Consumer callback
             */
            channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
                /**
                 * 复写这个方法，处理从mq中获取的消息
                 * @param consumerTag 消费者标记，可设可不设
                 * @param envelope 信封
                 * @param properties 消息属性，我们在生产者发送消息时可以设置
                 * @param body 消息体
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchange = envelope.getExchange();
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("MQ中消息id：" + deliveryTag);
                    System.out.println("交换机：" + exchange);
                    System.out.println("receive：" + new String(body, Charset.defaultCharset()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
