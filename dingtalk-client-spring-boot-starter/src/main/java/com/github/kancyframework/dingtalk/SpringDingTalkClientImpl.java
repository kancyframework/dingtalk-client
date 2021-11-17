package com.github.kancyframework.dingtalk;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

/**
 * SpringDingTalkClientImpl
 *
 * @author huangchengkang
 * @date 2021/11/16 23:22
 */
public class SpringDingTalkClientImpl extends DingTalkClientImpl implements SpringDingTalkClient {

    private RestTemplate restTemplate;

    public SpringDingTalkClientImpl(String accessToken) {
        super(accessToken);
    }

    public SpringDingTalkClientImpl(String accessToken, String secretKey) {
        super(accessToken, secretKey);
    }

    /**
     * 请求
     *
     * @param method         方法
     * @param url            网址
     * @param requestContent 请求内容
     * @param headerMap      标题映射
     * @param connectTimeout 连接超时
     * @param readTimeout    读超时
     * @return {@link String}
     */
    @Override
    protected String doRequest(String method, String url, String requestContent, Map<String, ?> headerMap,
                               int connectTimeout, int readTimeout) {
        if (Objects.equals(method, HttpMethod.POST.name())){
            return doPostRequest(url, requestContent, headerMap);
        } else if(Objects.equals(method, HttpMethod.GET.name())){
            return restTemplate.getForObject(url, String.class);
        } else {
           throw new UnsupportedOperationException("Not support http request method : " + method);
        }
    }

    private String doPostRequest(String url, String requestContent, Map<String, ?> headerMap) {
        Object requestBody = requestContent;
        if (!CollectionUtils.isEmpty(headerMap)){
            HttpHeaders headers = new HttpHeaders();
            headerMap.forEach((key, value) -> {
                if (Objects.nonNull(value)){
                    headers.add(key, String.valueOf(value));
                }
            });
            requestBody = new HttpEntity<>(requestContent, headers);
        }
        return restTemplate.postForObject(url, requestBody, String.class);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
