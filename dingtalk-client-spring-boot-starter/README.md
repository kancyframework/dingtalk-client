### dingtalk-client-spring-boot-starter

#### 1.快速开始

##### 1.1配置application.yml

```yml
# dingtalk client primary
dingtalk:
  access-token: 1676877387c8a486a8c81491def78a931d0a7df65e3eff0c3ef28c4a25b5cc50
  secret-key: SEC92e1603e992a14af2b27efdb6753bf08eadc694ee6dfc9cb458327899dbd2695
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
![](../images/quick_start.png)
