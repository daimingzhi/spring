# webSocket,基于stomp

1. 什么是stomp？
   - 我们可以类比TCP与Http协议，我们知道Http协议是基于TCP协议的，Http协议解决了 web 浏览器发起请求以及 web 服务器响应请求的细节，我们在编码时候只要关注我们要发送或接受的信息就行了，不需要关注那些细节
   - 直接使用 WebSocket（SockJS） 就很类似于 使用 TCP 套接字来编写 web 应用；因为没有高层协议，因此就需要我们定义应用间所发送消息的语义，还需要确保连接的两端都能遵循这些语义；
   - 同 HTTP 在 TCP 套接字上添加 请求-响应 模型层一样，STOMP 在 WebSocket 之上提供了一个基于 帧的线路格式层，用来定义消息语义；

2. STOMP帧格式

   - 基本格式

     ```
     COMMAND
     header1:value1
     header2:value2
     Body^@
     ```


   - ```
     // 发送消息
     SEND    -- 命令类型：发送
     destination:/queue/trade   -- 头信息：标明了目的地
     content-type:application/json  -- 头信息：标明数据交换格式
     content-length:44  -- 用来表示负载内容的 大小；
     {"action":"BUY","ticker":"MMM","shares",44}^@ -- 帧内容
     ```
