package com.dengjunwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
public class IssueApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IssueApplication.class, args);
        System.out.println("active profiles: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));
    }
}
