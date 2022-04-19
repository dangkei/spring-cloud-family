>最近在研究这个项目把原项目clone一个分支下来。然后参照原代码重写一遍学习
>并添加自己的注释和学习笔记

# Sample Credit Card application eco-system

This branch contains the new stack demo. To see the same demo with Spring-Cloud-Netflix stack, check out the branch `old-stack`.

这个分支包含新的技术栈演示，查看同样的演示使用Netflix技术栈，检出old-stack分支

After running all the apps execute POST at `localhost:9080/application` passing

运行起所有的app以后，使用post方法把cardApplication.json的内容作为body提交给 `localhost:9080/application`
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
http GET localhost:9083/ignored/test
```

```bash
http GET localhost:9083/ignored/test/allowed
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

## Client side load-balancing using LoadBalancerClient

- Ribbon used via `@LoadBalanced` `WebClient`
- Ribbon configuration modified via `@LoadBalancerClient`

## Apps communicating via Gateway:

- Routes have to be explicitly defined
- Possibility to configure routes either via properties or Java configuration
- All headers passed by default
- Routes matched using predicates, requests modified using filters

## Spring Cloud Circuit Breaker + Resilience4J

- Interactions defined using injected `CircuitBreakerFactory` via the `create()` method
- HTTP call and fallback method defined
- Circuit breaker configuration modified in `Customizer<CircuitBreaker` bean 
  in a `@Configuration` class 
- Resilience4J used underneath																																

## Micrometer + Prometheus

- HTTP traffic monitoring using Micrometer + Prometheus
- Added a Micrometer's `Timer` to `VerificationServiceClient`