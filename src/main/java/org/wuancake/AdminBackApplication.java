package org.wuancake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableCaching
@PropertySource("file:/etc/application.yml")
public class AdminBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminBackApplication.class, args);
    }

}
