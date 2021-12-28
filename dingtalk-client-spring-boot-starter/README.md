### dingtalk-client-spring-boot-starter

#### 1.快速开始

##### 1.1配置application.yml

```yml
# dingtalk client primary
dingtalk:
  access-token: 1676877387c8a486a8c81491def78a931d0a7df65e3eff0c3ef28c4a25b5cc51
  secret-key: SEC92e1603e992a14af2b27efdb6753bf08eadc694ee6dfc9cb458327899dbd2692
```

##### 1.2注入DingTalkClient

```java
/**
 * DingTalkClientTestRunner
 *
 * @author huangchengkang
 * @date 2021/11/17 20:36
 */
public class DingTalkClientTestRunner implements ApplicationRunner {
    @Autowired
    private DingTalkClient dingTalkClient;

    @Override
    public void run(ApplicationArguments args) {
        // 发生钉钉普通消息，并@所有人
        dingTalkClient.sendText("Hi DingTalkClient !", true);
    }
}
```

##### 1.3效果图
![](https://images.cnblogs.com/cnblogs_com/kancy/2069805/o_211228103839_quick_start.png)


#### 2.配置多个DingTalkClient

##### 2.1配置application.yml
- 配置三个DingTalkClient ： dingTalkClient、test1DingTalkClient、test2DingTalkClient
```yml
# dingtalk client primary
dingtalk:
  bean-name: dingTalkClient
  access-token: 1676877387c8a486a8c81491def78a931d0a7df65e3eff0c3ef28c4a25b5cc51
  secret-key: SEC92e1603e992a14af2b27efdb6753bf08eadc694ee6dfc9cb458327899dbd2692
  rest-template-bean-name: restTemplate
  description: 我是primary DingTalkClient，可以通过@Autowired直接注入
  clients:
    # dingtalk client test1
    test1:
      bean-name: test1DingTalkClient
      access-token: 1676877387c8a486a8c81491def78a931d0a7df65e3eff0c3ef28c4a25b5cc51
      secret-key: SEC92e1603e992a14af2b27efdb6753bf08eadc694ee6dfc9cb458327899dbd2692
    # dingtalk client test2
    test2:
      bean-name: test2DingTalkClient
      access-token: 1676877387c8a486a8c81491def78a931d0a7df65e3eff0c3ef28c4a25b5cc51
      secret-key: SEC92e1603e992a14af2b27efdb6753bf08eadc694ee6dfc9cb458327899dbd2692
```
##### 2.1配置说明
- `access-token`和`secret-key`是最重要的参数，用来处理请求认证
- `dingtalk.bean-name`的客户端是被设置成primary，当配置多个DingTalkClient时，默认导入的是dingTalkClient
- `dingtalk.description`属性没有实际意义，用来备注DingTalkClient
- `dingtalk.rest-template-bean-name`指定底层RestTemplate，默认为：`restTemplate`
- `dingtalk.clients`支持配置额外的客户端，默认的beanName为 clientName + DingTalkClient , 例如：`test1DingTalkClient`

##### 2.3使用 DingTalkClient

```java
/**
 * MultipleDingTalkClientTestRunner
 *
 * @author huangchengkang
 * @date 2021/11/17 20:36
 */
public class MultipleDingTalkClientTestRunner implements ApplicationRunner {
    @Autowired
    private DingTalkClient dingTalkClient;
    @Resource
    private DingTalkClient test1DingTalkClient;
    @Resource
    private DingTalkClient test2DingTalkClient;

    @Override
    public void run(ApplicationArguments args) {
        // 发生钉钉普通消息，并@所有人
        dingTalkClient.sendText("Hi DingTalkClient !", true);
        // 发生钉钉普通消息，并@所有人
        test1DingTalkClient.sendText("Hi test1DingTalkClient !", true);
        // 发生钉钉普通消息，并@所有人
        test2DingTalkClient.sendText("Hi test2DingTalkClient !", true);
    }
}
```