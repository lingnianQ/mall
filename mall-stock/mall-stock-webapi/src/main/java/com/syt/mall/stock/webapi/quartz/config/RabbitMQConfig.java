package com.syt.mall.stock.webapi.quartz.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ的配置
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/16 15:11
 */
@Configuration
public class RabbitMQConfig {
    // 一般在使用前,会将所有需要使用到的交换机,路由key和队列的名称声明为常量
    public static final String STOCK_EX = "stock_ex";
    public static final String STOCK_ROUT = "stock_rout";
    public static final String STOCK_QUEUE = "stock_queue";

    // 先声明交换机和队列这两个实际对象,保存到Spring容器
    @Bean
    public DirectExchange stockDirectExchange() {
        return new DirectExchange(STOCK_EX);
    }

    @Bean
    public Queue stockQueue() {
        return new Queue(STOCK_QUEUE);
    }

    // 最后绑定交换机和队列的关系,将路由Key对象保存到Spring容器
    @Bean
    public Binding stockBinding() {
        return BindingBuilder.bind(stockQueue())
                .to(stockDirectExchange()).with(STOCK_ROUT);
    }

}
