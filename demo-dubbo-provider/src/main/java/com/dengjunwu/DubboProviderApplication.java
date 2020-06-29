package com.dengjunwu;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
//@EnableDubboConfiguration
@EnableDubbo
public class DubboProviderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboProviderApplication.class, args);
        log.info("{} is active", Arrays.toString(run.getEnvironment().getActiveProfiles()));
    }
}
