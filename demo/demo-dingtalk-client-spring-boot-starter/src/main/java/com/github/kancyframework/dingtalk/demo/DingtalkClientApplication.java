package com.github.kancyframework.dingtalk.demo;

import com.github.kancyframework.dingtalk.DingTalkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class DingtalkClientApplication implements ApplicationRunner {

	@Autowired
	private DingTalkClient dingTalkClient;

	@Autowired
	private Map<String, DingTalkClient> dingTalkClients;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		dingTalkClient.sendText(dingTalkClient.toString(), true);

		System.out.println(dingTalkClients);

	}

	public static void main(String[] args) {
		SpringApplication.run(DingtalkClientApplication.class, args);
	}
}
