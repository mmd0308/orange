package cn.hengzq.orange.admin.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"cn.hengzq.orange.admin.module", "cn.hengzq.orange.admin.server"})
public class OrangeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeServerApplication.class, args);
        log.warn("orange-admin-server started successfully.");
    }

}
