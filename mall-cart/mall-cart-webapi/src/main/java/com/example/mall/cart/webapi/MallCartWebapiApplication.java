package com.example.mall.cart.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class MallCartWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCartWebapiApplication.class, args);
    }

}
