package com.dengjunw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class DemoElasticsearchApplication {
    public static void main(String args[]){
        ConfigurableApplicationContext run = SpringApplication.run(DemoElasticsearchApplication.class, args);
        log.info("{} is active", Arrays.toString(run.getEnvironment().getActiveProfiles()));
    }
}
