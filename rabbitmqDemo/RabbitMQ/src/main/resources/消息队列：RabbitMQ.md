# 消息队列：RabbitMQ

## 1.知识储备

**消息队列：**在消息的传输过程中保存消息的的容器。

这是一个较为经典的消费-生产者模型，说起来比较抽象，打个比方：A线程需要给B线程发送消息（A、B线程不一定是在同一台机器上的），A线程先把消息发送到消息队列服务器上，然后B线程去读取或是订阅消息服务器上消息队列中的消息，线程A和B之间并没有进行直接通信。MQ服务器在中间起到中继的作用。我们可以把MQ理解成为一个邮局+邮差的角色，只不过邮局传递消息用的是传统的信件，而MQ用的是二进制数据

RabbitMQ是AMQP（高级消息队列协议）的一个标准实现，关于它的快速入门，可以在这里查看：<http://www.rabbitmq.com/getstarted.html>。

**AMQP跟JMS**:：

​	**JMS**是SUN公司定义的一套规范，旨在为java应用提供统一的消息操作，包括create、send、receive等等，从使用角度看，JMS和JDBC担任差不多的角色，用户都是根据相应的接口可以和实现了JMS的服务进行通信，进行相关的操作。

 JMS通常包含如下一些角色：

| **Elements**            | **Notes**                                                    |
| ----------------------- | ------------------------------------------------------------ |
| JMS provider            | 实现了JMS接口的消息中间件，如ActiveMQ                        |
| JMS client              | 生产或者消费消息的应用                                       |
| JMS producer/publisher  | JMS消息生产者                                                |
| JMS consumer/subscriber | JMS消息消费者                                                |
| JMS message             | 消息，在各个JMS client传输的对象；                           |
| JMS queue               | Provider存放等待被消费的消息的地方                           |
| JMS topic               | 一种提供多个订阅者消费消息的一种机制；在MQ中常常被提到，topic模式。 |

​	**AMQP**（advanced message queuing protocol）在2003年时被提出，最早用于解决金融领不同平台之间的消息传递交互问题。顾名思义，AMQP是一种协议，更准确的说是一种binary wire-level protocol（链接协议）。这是其和JMS的本质差别，AMQP不从API层进行限定，而是直接定义网络交换的数据格式。这使得实现了AMQP的provider天然性就是跨平台的。意味着我们可以使用Java的AMQP provider，同时使用一个python的producer加一个rubby的consumer。从这一点看，AQMP可以用http来进行类比，不关心实现的语言，只要大家都按照相应的数据格式去发送报文请求，不同语言的client均可以和不同语言的server链接。在AMQP中，消息路由（messagerouting）和JMS存在一些差别，在AMQP中增加了Exchange和binding的角色。producer将消息发送给Exchange，binding决定Exchange的消息应该发送到那个queue，而consumer直接从queue中消费消息。queue和exchange的bind有consumer来决定。

 	JMS和AMQP的各项对比如下：

|              |                             JMS                              | AMQP                                                         |
| :----------: | :----------------------------------------------------------: | ------------------------------------------------------------ |
|     定义     |                           Java api                           | Wire-protocol                                                |
|    跨语言    |                              否                              | 是                                                           |
|    跨平台    |                              否                              | 是                                                           |
|    Model     |           Peer-2-Peer(点对点)，Pub/sub（发布订阅）           | 提供了五种消息模型：（后面会详细介绍）
（1）、direct exchange
（2）、fanout exchange
（3）、topic change
（4）、headers exchange
（5）、system exchange
本质来讲，后四种和JMS的pub/sub模型没有太大差别，仅是在路由机制上做了更详细的划分； |
| 支持消息类型 | 多种消息类型：TextMessage，MapMessage，BytesMessage，StreamMessage，ObjectMessage，Message （只有消息头和属性） | byte[]当实际应用时，有复杂的消息，可以将消息序列化后发送。   |
|   综合评价   | JMS 定义了JAVA API层面的标准；在java体系中，多个client均可以通过JMS进行交互，不需要应用修改代码，但是其对跨平台的支持较差； | AMQP定义了wire-level层的协议标准；天然具有跨平台、跨语言特性。 |

## 2.RabbitMQ基本结构
![](src/main/resources/1554530405252.png)
**组成部分说明如下：**

1. **Broker**:消息队列服务进程，此进程包含两部分，交换机及队列
2. **Exchange**:交换机，将消息按一定的路由规则转发到队列，对消息进行过滤
3. **Queue**: 消息队列，储存消息的队列，消息到达队列并转发给指定的消费方
4. **Producer**:生产者，将消息发送到MQ
5. **Consumer**:消费者，接受MQ中的消息

## 3.运行流程

1. 生产者：
   - 生产者跟Broker建立TCP连接
   - 生产者跟Broker建立通道Channel
   - 生产者通过通道发送消息到Broker，由交换机将消息进行转发
   - 交换机将消息转发到指定的队列

2. 消费者
   - 消费者跟Broker建立TCP连接
   - 消费者跟Broker建立通道Channel
   - 消费者监听指定的队列
   - 当有消息到达队列时，Broker将消息推送给消费者（**没有经过交换机**）
   - 消费者接受到消息

## 4.入门案例

1. 生产者代码

```java
public class RabbitmqApplication {

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
            // 交换机：不声明则为默认的交换机
            // 声明队列
            /**
             * @params 参数列表：
             * String queue: 声明的队列名称
             * boolean durable：消息是否持久化，若设置为true，则MQ重启后，队列仍然存在
             * boolean exclusive：是否独占连接，设置为true,连接关闭则队列被删除，一般用于临时队列的创建，跟autoDelete配合使用
             * boolean autoDelete：是否自动删除，设置为true,则队列不使用就自动删除，一般用于临时队列的创建
             * Map<String, Object> arguments：设置队列的一些扩展参数
             */
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 生产者发送消息
            String message = "Hello World!";
            /**
             * @params
             *String exchange,  交换机名称
             *String routingKey, 路由key
             *BasicProperties props, 消息的一些属性
             *byte[] body 消息体
             *
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

2. 消费者代码

```java
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
```

