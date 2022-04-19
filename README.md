>最近在研究这个项目把原项目clone一个分支下来。然后参照原代码重写一遍学习
>并添加自己的注释和学习笔记

# Sample Credit Card application eco-system

This branch contains the new stack demo. To see the same demo with Spring-Cloud-Netflix stack, check out the branch `old-stack`.

这个分支包含新的技术栈演示，查看同样的演示使用Netflix技术栈，检出old-stack分支

After running all the apps execute POST at `10.192.15.124:9080/application` passing

运行起所有的app以后，使用post方法把cardApplication.json的内容作为body提交给 `10.192.15.124:9080/application`
`cardApplication.json` as body.

```bash
http POST 10.192.15.124:9080/application < cardApplication.json
```

```bash
ab -p cardApplication.json -T application/json -c 10 -n 20000 http://localhost:9080/application
```

- new card applications registered via `card-service`
- 新卡申请注册通过card-service服务
- user registered via `user-service`
- 用户注册通过user-service服务
- `fraud-service` called by `card-service` and `user-service` to verify 
  card applications and new users
- fraud-service服务被card-service和user-service调用，用来验证新卡和新用户的申请

If you want to run a bigger number of requests, you can use the `ab` benchmarking tool:
如果你需要运行大数据量的请求，可以使用ab

```bash
ab -p cardApplication.json -T application/json -c 10 -n 20000 http://localhost:9080/application
```

```bash
http GET 10.192.15.124:9083/ignored/test
```

```bash
http GET 10.192.15.124:9083/ignored/test/allowed
```

- `ignored` service with `test` endpoint returning 404 via Proxy and `/test/allowed` 
  endpoint returning response from the service.
  ignored服务的test断点通过Proxy和/test/allowed端服务返回404

```
+-------+                         +-------------+       +-------------+          +-------+             +---------------+ +-----------------+ +-------+
| User  |                         | CardService |       | UserService |          | Proxy |             | FraudVerifier | | IgnoredService  | | PRoxy |
+-------+                         +-------------+       +-------------+          +-------+             +---------------+ +-----------------+ +-------+
    |                                    |                     |                     |                         |                  |              |
    | Register application               |                     |                     |                         |                  |              |
    |----------------------------------->|                     |                     |                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    | Create new user     |                     |                         |                  |              |
    |                                    |------------------------------------------>|                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     |     Create new user |                         |                  |              |
    |                                    |                     |<--------------------|                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     | Verify new user     |                         |                  |              |
    |                                    |                     |-------------------->|                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     |                     | Verify new user         |                  |              |
    |                                    |                     |                     |------------------------>|                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     |                     |           User verified |                  |              |
    |                                    |                     |                     |<------------------------|                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     |       User verified |                         |                  |              |
    |                                    |                     |<--------------------|                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |        User created |                     |                         |                  |              |
    |                                    |<--------------------|                     |                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     |                     |                         |                  | User created |
    |                                    |<------------------------------------------------------------------------------------------------------|
    |                                    |                     |                     |                         |                  |              |
    |                                    | Verify card application                   |                         |                  |              |
    |                                    |-------------------------------------------------------------------->|                  |              |
    |                                    |                     |                     |                         |                  |              |
    |                                    |                     |                     Card application verified |                  |              |
    |                                    |<--------------------------------------------------------------------|                  |              |
    |                                    |                     |                     |                         |                  |              |
    |        Card application registered |                     |                     |                         |                  |              |
    |<-----------------------------------|                     |                     |                         |                  |              |
    |                                    |                     |                     |                         |                  |              |
```

```
+-------+                         +-------+         +-----------------+
| User  |                         | Proxy |         | IgnoredService  |
+-------+                         +-------+         +-----------------+
    |                                 |                      |
    | IgnoredService/Test             |                      |
    |-------------------------------->|                      |
    |                                 |                      |
    |                             404 |                      |
    |<--------------------------------|                      |
    |                                 |                      |
    | IgnoredService/Test/Allowed     |                      |
    |-------------------------------->|                      |
    |                                 |                      |
    |                                 | Get allowed          |
    |                                 |--------------------->|
    |                                 |                      |
    |                         Allowed |                      |
    |<--------------------------------|                      |
    |                                 |                      |
```

# Setup using new stack
	应用了新的技术栈创建
## Client side load-balancing using LoadBalancerClient
客户端负载均衡使用LoadBalancerClient
- Ribbon used via `@LoadBalanced` `WebClient`
- Ribbon configuration modified via `@LoadBalancerClient`

## Apps communicating via Gateway:
	应用间通信通过Gateway

- Routes have to be explicitly defined
	- 路由必须强制生命
- Possibility to configure routes either via properties or Java configuration
	- 不管属性文件还是Java类配置路由都可以
- All headers passed by default
	- 所有的header传递缺省值
- Routes matched using predicates, requests modified using filters
	- 路由匹配修饰？ 使用过滤器编辑请求？
	
## Spring Cloud Circuit Breaker + Resilience4J
    spring cloud的环形断路器+Resilience4J
- Interactions defined using injected `CircuitBreakerFactory` via the `create()` method
- 已定义互动通过created()方法注入CircuitBreakerFactory
- HTTP call and fallback method defined
- 已定义HTTP调用和返回方法
- Circuit breaker configuration modified in `Customizer<CircuitBreaker` bean 
  in a `@Configuration` class 
- 通过在一个配置类中修改Customizer<CircuitBreaker来修改断路器的配置
- Resilience4J used underneath																																
- 后面使用了Resilience4J
## Micrometer + Prometheus
HTTP通信使用千分尺和普罗米修斯 添加千分尺的Timer来验证客户端服务
- HTTP traffic monitoring using Micrometer + Prometheus
- Added a Micrometer's `Timer` to `VerificationServiceClient`