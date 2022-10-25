package com.syt.mall.stock.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -@EnableDubbo
 * 如果当前项目是Dubbo中的生产者，必须添加@EnableDubbo注解
 * 添加之后，当前项目对应的所有服务才可以正确注册到nacos
 *
 * @author sytsn
 */

@EnableDubbo
@SpringBootApplication
public class MallStockWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallStockWebapiApplication.class, args);
    }

}
