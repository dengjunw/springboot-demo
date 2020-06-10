package com.dengjunwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaServer
public class ServerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ServerApp.class, args);
        System.out.println("{} is active " + Arrays.toString(run.getEnvironment().getActiveProfiles()));
    }
}