package com.roommake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class RoommakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoommakeApplication.class, args);
    }
}
