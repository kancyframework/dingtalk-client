package com.github.kancyframework.dingtalk.properties;

import lombok.Data;

import java.util.Map;

/**
 * DingTalkProperties
 *
 * @author huangchengkang
 * @date 2021/11/16 23:33
 */
@Data
//@ConfigurationProperties(prefix = "dingtalk")
//@EnableConfigurationProperties(DingTalkProperties.class)
public class DingTalkProperties {

    private Map<String, DingTalkClientConfig> clients;
    /**
     * bean名称
     */
    private String beanName = "dingTalkClient";
    /**
     * restTemplate bean名称
     */
    private String restTemplateBeanName = "restTemplate";
    /**
     * 访问令牌 - 必要
     */
    private String accessToken;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * url
     */
    private String url;
    /**
     * 描述 - 非必要
     */
    private String description;

    @Data
    public static class DingTalkClientConfig {
        /**
         * bean名称
         */
        private String beanName;
        /**
         * restTemplate bean名称
         */
        private String restTemplateBeanName = "restTemplate";
        /**
         * 访问令牌
         */
        private String accessToken;
        /**
         * 密钥
         */
        private String secretKey;
        /**
         * 描述
         */
        private String description;

    }

}
