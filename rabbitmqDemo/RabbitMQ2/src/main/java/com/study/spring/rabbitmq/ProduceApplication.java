package com.study.spring.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * RabbitMQ生产者代码测试
 *
 * @author daimzh
 */
public class ProduceApplication {

    public static final String QUEUE_NAME_WRITE = "write";
    public static final String QUEUE_NAME_SHOW = "show";
    public static final String EXCHANGE_FANOUT_NAME = "FANOUT";

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
            // 声明交换机
            /**
             * String exchange, 交换机名称
             * String type,  交换机类型
             * boolean durable  是否持久化
             */
            channel.exchangeDeclare(EXCHANGE_FANOUT_NAME, BuiltinExchangeType.FANOUT, false);
            // 声明队列
            /**
             * @params 参数列表：
             * String queue: 声明的队列名称
             * boolean durable：消息是否持久化，若设置为true，则MQ重启后，队列仍然存在
             * boolean exclusive：是否独占连接，设置为true,连接关闭则队列被删除，一般用于临时队列的创建，跟autoDelete配合使用
             * boolean autoDelete：是否自动删除，设置为true,则队列不使用就自动删除，一般用于临时队列的创建
             * Map<String, Object> arguments：设置队列的一些扩展参数
             */
            channel.queueDeclare(QUEUE_NAME_WRITE, true, false, false, null);
            channel.queueDeclare(QUEUE_NAME_SHOW, true, false, false, null);

            // 交换机跟队列绑定,或者说创建了一个binding，
            // 其实就是交换机跟队列的一个绑定关系
            /**
             * String queue,  队列名称
             * String exchange,  交换机名称
             * String routingKey 路由key,发布订阅模式中我们暂且设置为""
             */
            channel.queueBind(QUEUE_NAME_WRITE, EXCHANGE_FANOUT_NAME, "");
            channel.queueBind(QUEUE_NAME_SHOW, EXCHANGE_FANOUT_NAME, "");

            // 生产者发送消息
            String message = "Hello World Pub/Sub!";
            message = String.join(" ", message);

            channel.basicPublish(EXCHANGE_FANOUT_NAME, "", null, message.getBytes());
            /**
             * @params
             *String exchange,  交换机名称
             *String routingKey, 路由key
             *BasicProperties props, 消息的一些属性
             *byte[] body 消息体
             *
             */
            channel.basicPublish("", "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
