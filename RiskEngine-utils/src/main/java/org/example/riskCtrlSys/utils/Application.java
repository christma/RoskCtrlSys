package org.example.riskCtrlSys.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example.riskCtrlSys.utils.redis")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
