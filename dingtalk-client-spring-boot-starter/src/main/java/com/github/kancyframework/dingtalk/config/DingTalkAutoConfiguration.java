package com.github.kancyframework.dingtalk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * DingTalkAutoConfiguration
 *
 * @author huangchengkang
 * @date 2021/11/16 23:32
 */
@Import(DingTalkClientDefinitionRegistrar.class)
@Configuration
public class DingTalkAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
