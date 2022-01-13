package com.github.kancyframework.dingtalk.demo.runner;

import com.github.kancyframework.dingtalk.DingTalkClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * DingTalkClientDemo
 *
 * @author huangchengkang
 * @date 2021/11/17 20:36
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DingTalkClientTestRunner implements ApplicationRunner {

    private final DingTalkClient dingTalkClient;

    private final Map<String, DingTalkClient> dingTalkClients;

    @Override
    public void run(ApplicationArguments args) {
        log.info("dingTalk clients:{}", dingTalkClients);

        // 发生钉钉普通消息，并@所有人
        dingTalkClient.sendText("Hi DingTalkClient !", true);
    }
}
