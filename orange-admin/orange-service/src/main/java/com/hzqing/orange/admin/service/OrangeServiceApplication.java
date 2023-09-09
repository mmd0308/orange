package com.hzqing.orange.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.hzqing.orange.admin.module", "com.hzqing.orange.admin.service"})
public class OrangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeServiceApplication.class, args);
        log.warn("orange-admin-service started successfully.");
    }

}
