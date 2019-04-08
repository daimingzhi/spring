## Spring-Data-JPA学习

​	jpa简单的命名规则如下，这个不多做介绍，放在这里也是给自己以后查找起来方便，这篇文章主要介绍之前一直忽略了的几个点，像`@NoRepositoryBean`这个注解，以及怎么自定义Repository的实现。

| 关键字            | 方法命名                       | sql where字句              |
| ----------------- | ------------------------------ | -------------------------- |
| And               | findByNameAndPwd               | where name= ? and pwd =?   |
| Or                | findByNameOrSex                | where name= ? or sex=?     |
| Is,Equals         | findById,findByIdEquals        | where id= ?                |
| Between           | findByIdBetween                | where id between ? and ?   |
| LessThan          | findByIdLessThan               | where id < ?               |
| LessThanEquals    | findByIdLessThanEquals         | where id <= ?              |
| GreaterThan       | findByIdGreaterThan            | where id > ?               |
| GreaterThanEquals | findByIdGreaterThanEquals      | where id > = ?             |
| After             | findByIdAfter                  | where id > ?               |
| Before            | findByIdBefore                 | where id < ?               |
| IsNull            | findByNameIsNull               | where name is null         |
| isNotNull,NotNull | findByNameNotNull              | where name is not null     |
| Like              | findByNameLike                 | where name like ?          |
| NotLike           | findByNameNotLike              | where name not like ?      |
| StartingWith      | findByNameStartingWith         | where name like '?%'       |
| EndingWith        | findByNameEndingWith           | where name like '%?'       |
| Containing        | findByNameContaining           | where name like '%?%'      |
| OrderBy           | findByIdOrderByXDesc           | where id=? order by x desc |
| Not               | findByNameNot                  | where name <> ?            |
| In                | findByIdIn(Collection<?> c)    | where id in (?)            |
| NotIn             | findByIdNotIn(Collection<?> c) | where id not  in (?)       |
| True              | findByAaaTrue                  | where aaa = true           |
| False             | findByAaaFalse                 | where aaa = false          |
| IgnoreCase        | findByNameIgnoreCase           | where UPPER(name)=UPPER(?) |

------

### @NoRepositoryBean

​	我们查看`JpaRepository`这个类的继承关系可以看到下面这张图

![继承树](C:\Users\daimzh\Desktop\继承树.png)
​	而在`JpaRepository`,`PagingAndSortingRepository`,`QueryByExampleExecutor`，这三个接口上我们都能发现这个注解`@NoRepositoryBean`，这个注解到底有上面用呢？

​	我们来看下官网的解释：

![官网解释](C:\Users\daimzh\Desktop\官网解释.png)

​	上面这段话大致的意思是：只要在相应的Repository接口上添加了这个注解，Spring就不会在运行时为这个接口创建对应的实例。

​	看到这里估计有的同学更加迷惑了，这又是什么意思呢？我们来看下面这段代码：

我定义了一个Repository接口

```java
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据id查询
     *
     * @param part
     * @return
     */
    List<User> findFirstByUseEmailLike(String part);
}
```

启动类如下：

```java

```

断点调试如下：

![debug](C:\Users\daimzh\Desktop\debug.png)

​	可以看到，虽然我们定义了一个userRepository接口，但是Spring为我们生成了一个代理对象，并且这个代理对象代理是`org.springframework.data.jpa.repository.support.SimpleJpaRepository`这个类。

​	我们看下`SimpleJpaRepository`这个类的继承关系：

![pig](C:\Users\daimzh\Desktop\pig.png)

​	可以发现，SimpleJpaRepository实现了JpaRepository这个接口。

​	其实在程序初始化的时候，SpringBoot会通过这个注解`@EnableJpaRepositories(basePackages = {"com.study.spring.springdatajpa.repository"})`将我们配置的包下的所有继承0了JpaRepository这个接口的接口，注册到spring容器中，然后以SimpleJpaRepository为目标对象创建代理对象。

​	我们知道对应的接口有3个，为什么它们没被创建代理对象呢？这样就要说到我们提到的注解`@NoRepositoryBean`了,`JpaRepository`,`PagingAndSortingRepository`,`QueryByExampleExecutor`这个3个接口都被这个注解标注了，所以不会被创建代理对象，这也是这个注解的作用

### 利用`@NoRepositoryBean`来自定义一个Repository的实现

​	我们要达到的目的就是，定义一个Repository接口（仿JpaRepository），只要有别的接口继承了这个类，就自动拥有这个接口对应的实现类（仿SimpleJpaRepository）中的方法。

​	代码如下：

1. 定义接口：

   ```java
   package com.wisely.support;
   import java.io.Serializable;
   import org.springframework.data.domain.Page;
   import org.springframework.data.domain.Pageable;
   import org.springframework.data.jpa.repository.JpaRepository;
   import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
   import org.springframework.data.repository.NoRepositoryBean;
   
   @NoRepositoryBean  //指明当前这个接口不是领域类的接口
   //我们自定义的CustomRepository继承了JpaRepository接口，具备JPA的能力
   public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
       //要定义的数据操作方法在接口中定义
       void doWithId(ID id);
   }
   ```

2. 定义接口实现类

   ```java
   //要实现 CustomRepository接口，继承SimpleJpaRepository类让我们可以使用其提供的方法（如findAll）
   public class CustomRepositoryImpl<T, ID extends Serializable>
           extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {
   
       //数据操作方法会用到entityManager,我们这里没有用到
       private final EntityManager entityManager;
   
       //CustomRepositoryImpl的构造函数，
       // 需当前处理的领域类和entityManager作为构造参数，在这里也给entityManager赋值了
       public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
           super(domainClass, entityManager);
           this.entityManager = entityManager;
       }
   
       @Override
       public void doWithId(ID id) {
           //定义数据访问操作，如调用findAll方法并构造一些查询条件
           System.out.println("拿到id干点事儿");
       }
   
   }
   ```

3. 自定义一个RepositoryFactoryBean

   ```java
   //自定义CustomRepositoryFactoryBean,继承JpaRepositoryFactoryBean
   public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
           extends JpaRepositoryFactoryBean<T, S, ID> {
   
       public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
           super(repositoryInterface);
       }
   
       @Override
       //重写createRepositoryFactory方法，用当前的CustomRepositoryFactory创建实例
       protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
           return new CustomRepositoryFactory(entityManager);  //该类见下面的定义
       }
   
       //定义一个私有的静态类，并继承JpaRepositoryFactory
       //复写其中两个核心方法
       private static class CustomRepositoryFactory extends JpaRepositoryFactory {
   
           //构造函数
           public CustomRepositoryFactory(EntityManager entityManager) {
               super(entityManager);
           }
   
           @Override
           protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
               return new CustomRepositoryImpl<>(information.getDomainType(), entityManager);
           }
   
           @Override
           protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {// 获得当前自定义类的类型
               return CustomRepositoryImpl.class;
           }
       }
   }
   ```

4. 启动类代码，注意上面的注解

   ```java
   @SpringBootApplication
   //@EnableJpaRepositories(basePackages = //{"com.study.spring.springdatajpa.repository"})
   @EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
   public class SpringDataJpaApplication implements ApplicationRunner {
   
       @Autowired
       UserRepository userRepository;
   
       public static void main(String[] args) {
           SpringApplication.run(SpringDataJpaApplication.class, args);
       }
   
       @Override
       public void run(ApplicationArguments args) throws Exception {
           userRepository.doWithId(11);
       }
   }
   ```


运行程序打印结果如下：

```
拿到id干点事儿
```

