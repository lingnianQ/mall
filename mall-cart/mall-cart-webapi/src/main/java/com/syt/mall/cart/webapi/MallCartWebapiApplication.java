package com.syt.mall.cart.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDubbo
@SpringBootApplication
@EnableKafka
@EnableScheduling
public class MallCartWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCartWebapiApplication.class, args);
    }

}
