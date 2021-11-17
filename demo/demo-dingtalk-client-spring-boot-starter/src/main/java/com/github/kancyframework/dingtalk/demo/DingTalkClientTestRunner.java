package com.github.kancyframework.dingtalk.demo;

import com.github.kancyframework.dingtalk.DingTalkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * DingTalkClientDemo
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
