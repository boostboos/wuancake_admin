package org.wuancake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class AdminBackApplication { 

    public static void main(String[] args) {
        SpringApplication.run(AdminBackApplication.class, args);
    }
}
