package com.dengjunwu;

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
@EnableDubboConfiguration
public class DubboConsumerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboConsumerApplication.class, args);
        log.info("{} is active", Arrays.toString(run.getEnvironment().getActiveProfiles()));
    }
}
