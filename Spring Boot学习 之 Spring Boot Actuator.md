Spring Boot学习 之 Spring Boot Actuator（一）

**Spring Boot版本：2.1.4.RELEASE**

#### 启用：

`spring-boot-actuator`模块提供了一系列的用于监控的端点。最简单的开启这个功能的方法就是，在pom文件中添加如下的依赖。

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
</dependencies>
```

### 端点：

#### 开启/禁用端点

​	Actuator 的端点让你能够监控你的应用并能跟你的引用进行交互，Spring Boot包含了大量的内置的端点，而且你也能添加定制的端点。例如：`health`端点提供了应用的健康指标信息。

​	每个独立的端点都能被禁用或启用。当一个端点被禁用时，同时也代表了对应的端点不会被创建，对应的bean不会存在容器中。为了能远程访问一个端点，我们也会通过[JMX](https://baike.baidu.com/item/JMX/2829357?fr=aladdin)或者HTTP的方式将其暴露出去。大多数的应用选择HTTP，**在选择HTTP的情况下，端点的ID会默认加一个`/actuator`的前缀。例如，默认的`health`端点会被映射到`/actuator/health`路径上**

​	下面列举了通过浏览器访问（get方法）部分端点相关的信息：

​	有关端点的详细描述可参考官网：[端点](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/actuator-api//html/)，[相关博客](https://www.jianshu.com/p/31832dc1d30e)

| PATH（通过浏览器直接访问） | 描述                                                         | 是否启用 |
| :------------------------- | ------------------------------------------------------------ | -------- |
| **/actuator**              | 端点信息汇总，严格意义上来说不算一个端点，我在这里把他归到端点中一起 | 是       |
| /actuator/caches           | 获取所有的 Cachemanager                                      | 是       |
| /actuator/conditions       | 展示了自动配置相关的信息，可以看到哪些类进行了自动配置，哪些类没有进行自动配置，并且会标明没有进行自动配置的原因 | 是       |
| /actuator/beans            | 展示了容器中bean                                             | 是       |
| /actuator/configprops      | 展示了应用中@ConfigurationProperties注解的相关信息           | 是       |
| /actuator/info             | 获取应用程序定制信息                                         | 是       |
| /actuator/health           | 展示应用的健康信息                                           | 是       |
| /actuator/metrics          | 报告各种应用程序度量信息，如：内存用量、HTTP 请求计数        | 是       |
| /actuator/mappings         | 描述全部的 URL 路径，以及它们和控制器(包含Actuator端点)的映射关系 | 是       |
| /actuator/shutdown         | 关闭应用（此方法只能通过POST方法调用且默认为禁用状态）       | 否       |

​	上面说了，默认情况下，`shutdown`端点是关闭的，我们如何开启一个端点呢？

```properties
# 开启shutdown端点
management.endpoint.shutdown.enabled=true
```

​	在表格中我们可以知道，大部分端点默认情况下是开启的，那么我们如何只开启某个我们想要的端点呢？

```properties
# 关闭所有端点
management.endpoints.enabled-by-default=false
# 开启info端点
management.endpoint.info.enabled=true
```

​	经过上面的操作，我们就可以只开启`info`端点。

#### 暴露端点：

​	默认情况下，只有`health`,`info`端点会暴露出来（采用http的情况下），默认的配置为：

```yml

```

​	暴露所有端点:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

​	暴露我们想要的指定的端点，也可以仿照端点的相关操作，这里有所不同的是，我们要先暴露所有端点，然后再排除我们不需要暴露的端点

```yaml
# 暴露了除了health,beans之外的端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
        exclude: ["beans","health"]
```

​	我们看下面的例子：

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
        exclude: ["health","beans"]
    enabled-by-default: false
  endpoint:
    conditions:
      enabled: true
    configprops:
      enabled: true
```

我们预期的结果，应该是一个端点都没有，启动项目，看到日志如下：

```

```

可以发现，一个端点都没有暴露出去，这是因为我们配置中暴露的端点都没有开启。

关于端点的暴露，我们要知道的是：

1. 即使我们的应用是公开的，我们也需要保护好我们的端点
2. 如果我们想定制的做一些端点的暴露策略，我们可以注册一个`EndponitFilte`

### 端点保护：

​	我们应该像保护其他的敏感的URL一样保护我们的HTTP端点，如果我们当前采用了Spring Security，则端点会被默认的保护起来。如果我们想为端点配置定制的安全策略，例如，只允许固定角色的用户访问，Spring Boot提供了一些能跟Spring Securit结合使用的便利的``RequestMatcher` `对象。

​	一个典型的Spring Security的配置如下：

```java
@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
				.anyRequest().hasRole("ENDPOINT_ADMIN")
				.and()
			.httpBasic();
	}

}
```

​	上面的这段配置，使用了`EndpointRequest.toAnyEndpoint()`去匹配到任意端点的一起请求，并确保它具有`ENDPOINT_ADMIN`权限才能访问我们的端点。

​	当然，我们如果不想对我们的端点进行权限保护的话，可以进行如下配置：

```java

```

### 端点配置：

​	对于**不带任何参数**的`读取`操作,端点自动缓存对其响应。要配置端点缓存响应的时间，请使用`cache.time-live`属性。以下示例将`beans`端点缓存的生存时间设置为10秒：

```properties

```

​	遵循这种格式：

```properties

```

​	我们需要注意的是：

> 在进行经过验证的HTTP请求时，`Principal`将被视为端点的`输入`，因此不会缓存响应。

​	