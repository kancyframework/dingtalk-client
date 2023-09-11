package com.github.kancyframework.dingtalk.config;

import com.github.kancyframework.dingtalk.DingTalkClient;
import com.github.kancyframework.dingtalk.SpringDingTalkClientImpl;
import com.github.kancyframework.dingtalk.properties.DingTalkProperties;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * DingTalkClientDefinitionRegistrar
 *
 * @author huangchengkang
 * @date 2021/11/16 23:44
 */
public class DingTalkClientDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private DingTalkProperties dingTalkProperties;

    /**
     * Register bean definitions as necessary based on the given annotation metadata of
     * the importing {@code @Configuration} class.
     * @param importingClassMetadata annotation metadata of the importing class
     * @param registry               current bean definition registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (Objects.isNull(this.dingTalkProperties)){
            return;
        }
        // 注册主要的DingTalkClient
        registerPrimaryDingTalkClientBean(registry);

        // 注册其他的DingTalkClient
        Map<String, DingTalkProperties.DingTalkClientConfig> configs = dingTalkProperties.getClients();
        if (!CollectionUtils.isEmpty(configs)){
            configs.forEach((key, config) -> {
                if (!StringUtils.hasText(config.getBeanName())) {
                    config.setBeanName(String.format("%s%s", key, DingTalkClient.class.getSimpleName()));
                }

                String accessToken = config.getAccessToken();
                String secretKey = config.getSecretKey();
                if(StringUtils.hasText(accessToken)){
                    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SpringDingTalkClientImpl.class);
                    beanDefinitionBuilder.addConstructorArgValue(accessToken);
                    beanDefinitionBuilder.addConstructorArgValue(secretKey);
                    if (StringUtils.hasText(dingTalkProperties.getUrl())){
                        beanDefinitionBuilder.addConstructorArgValue(dingTalkProperties.getUrl());
                    }
                    beanDefinitionBuilder.addPropertyReference("restTemplate", config.getRestTemplateBeanName());
                    BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
                    registry.registerBeanDefinition(config.getBeanName(), beanDefinition);
                }
            });
        }
    }

    private void registerPrimaryDingTalkClientBean(BeanDefinitionRegistry registry) {
        String accessToken = dingTalkProperties.getAccessToken();
        if (StringUtils.hasText(accessToken)){
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SpringDingTalkClientImpl.class);
            beanDefinitionBuilder.addConstructorArgValue(accessToken);
            beanDefinitionBuilder.addConstructorArgValue(dingTalkProperties.getSecretKey());
            if (StringUtils.hasText(dingTalkProperties.getUrl())){
                beanDefinitionBuilder.addConstructorArgValue(dingTalkProperties.getUrl());
            }
            beanDefinitionBuilder.addPropertyReference("restTemplate", dingTalkProperties.getRestTemplateBeanName());
            beanDefinitionBuilder.setPrimary(true);
            BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

            String beanName = dingTalkProperties.getBeanName();
            if (!StringUtils.hasText(beanName)){
                beanName = StringUtils.capitalize(DingTalkClient.class.getSimpleName());
                dingTalkProperties.setBeanName(beanName);
            }
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        initDingTalkProperties(environment);
    }

    private void initDingTalkProperties(Environment environment) {
        BindResult<DingTalkProperties> dingtalk =
                Binder.get(environment).bind("dingtalk", DingTalkProperties.class);
        if (dingtalk.isBound()){
            this.dingTalkProperties = dingtalk.get();
        }
    }
}
