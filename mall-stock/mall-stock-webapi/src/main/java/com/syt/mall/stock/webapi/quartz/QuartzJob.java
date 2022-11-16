package com.syt.mall.stock.webapi.quartz;

import com.syt.mall.commons.pojo.stock.model.Stock;
import com.syt.mall.stock.webapi.quartz.config.RabbitMQConfig;
import org.apache.commons.lang.math.RandomUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/11 21:00
 */
public class QuartzJob implements Job {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    static int i = 1;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //quartz任务演示
        System.out.println("Quartz任务演示, 当前时间: " + LocalDateTime.now() + "-------------  i: " + i);

        Stock stock = new Stock();
        stock.setId(i++);
        stock.setCommodityCode("PC100");
        stock.setReduceCount(RandomUtils.nextInt(10) + 1);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.STOCK_EX,
                RabbitMQConfig.STOCK_ROUT,
                stock
        );
        System.out.println("发送消息完成:  " + stock);
    }
}
