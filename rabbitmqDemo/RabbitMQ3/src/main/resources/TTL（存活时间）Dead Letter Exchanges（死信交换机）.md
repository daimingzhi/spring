## TTL（存活时间）Dead Letter Exchanges（死信交换机）

### [TTL（Time-To-Live and Expiration）](<https://www.rabbitmq.com/ttl.html>)：

RabbitMQ既能对队列设置TTL也能对消息设置TTL，消息TTL可以应用于单个队列、一组队列或应用于逐个消息。

**如何给消息设置TTL？**

| rabbitmqctl           | `rabbitmqctl set_policy TTL ".*" '{"message-ttl":60000}' --apply-to queues` |
| --------------------- | ------------------------------------------------------------ |
| rabbitmqctl (Windows) | `rabbitmqctl set_policy TTL ".*" "{""message-ttl"":60000}" --apply-to queues` |

或者

```java
byte[] messageBodyBytes = "Hello, world!".getBytes();
AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                                   .expiration("60000")
                                   .build();
channel.basicPublish("my-exchange", "routing-key", properties, messageBodyBytes);
```

**如何给队列设置TTL？**

| rabbitmqctl           | `rabbitmqctl set_policy expiry ".*" '{"expires":1800000}' --apply-to queues` |
| --------------------- | ------------------------------------------------------------ |
| rabbitmqctl (Windows) | `rabbitmqctl.bat set_policy expiry ".*" "{""expires"":1800000}" --apply-to queues` |

或者：

```java
Map<String, Object> args = new HashMap<String, Object>();
args.put("x-message-ttl", 60000);
channel.queueDeclare("myqueue", false, false, false, args);
```

### 死信队列：跟死信交换机绑定的队列

### [死信交换机：](<https://www.rabbitmq.com/dlx.html>)

当以下的任意情况发生的时候，意味着队列中的该消息变成了**死信**

1. 消费者调用了`basic.reject/basic.nack` 并且设置`requeue`为`false`(不重回队列)的时候，消息就会进入死信队列 。

2. 消息队列`TTL`过期或者消息有效期过期。
3. 队列达到最大的长度，并且我们没有设置自动拒绝消息的时候，队首的消息就会进入死信队列 。

死信交换机其实就是一个正常的交换机，我们可以像申明一个正常交换机一样申明它。

**怎么给队列申明死信交换机？**

| rabbitmqctl           | `rabbitmqctl set_policy DLX ".*" '{"dead-letter-exchange":"my-dlx"}' --apply-to queues` |
| --------------------- | ------------------------------------------------------------ |
| rabbitmqctl (Windows) | `rabbitmqctl set_policy DLX ".*" "{""dead-letter-exchange"":""my-dlx""}" --apply-to queues` |

或者：

```java
channel.exchangeDeclare("some.exchange.name", "direct");

Map<String, Object> args = new HashMap<String, Object>();
args.put("x-dead-letter-exchange", "some.exchange.name");
channel.queueDeclare("myqueue", false, false, false, args);
```

我们也可以给这个交换机申明routingKey：

```java
args.put("x-dead-letter-routing-key", "some-routing-key");
```

利用TTL跟死信交换机我们可以实现消息的延时处理：

大致思路如下：

1. 声明一个延时处理的队列

   其实就是在申明队列的时候指定消息的存活时间，伪代码如下：

   ```java
   map.put("x-message-ttl", delayTime);
   ```

2. 在申明这个队列时，同时指定死信交换机跟RoutingKey

   ```java
    map.put("x-dead-letter-exchange", "dead-exchange");
    map.put("x-dead-letter-routing-key", "dead");
    queueDeclare("delay-queue", true, false, false, map)
   ```

3. 申明一个队列跟死信交换机绑定，作为死信队列

   ```java
   queueDeclare("dead-queue", true, false, false, null)
   queueBind("dead-queue", "dead-exchange", "dead", null)
   ```

