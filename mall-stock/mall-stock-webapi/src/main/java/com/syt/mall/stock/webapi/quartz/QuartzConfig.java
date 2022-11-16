package com.syt.mall.stock.webapi.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QuartzConfig 绑定调用方法和出发的关系
 * 这个触发实际上由被Spring管理的Scheduler对下调度的
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/11 21:04
 */
@Configuration
public class QuartzConfig {
    // 配置的核心是一个JobDetail和一个Trigger对象
    // JobDetail是任务内容,Trigger表示触发实际

    @Bean
    public JobDetail showTime() {
        System.out.println("!!!!!!!!!!!!!!!!!");
        // 利用JobBuilder类的newJob方法指定要运行的Job实现类的反射,我们编写的就是QuartzJob
        return JobBuilder.newJob(QuartzJob.class)
                // 需要为当前任务起个名字,方便Quartz调用
                .withIdentity("dateTime")
                // 默认情况下,JobDetail对象生成后,如果没有触发器绑定会被立即移除
                // 设置storeDurably方法后,当前JobDetail对象生成后,不会被移除了
                .storeDurably()
                .build();
    }

    // 下面开始编写触发器对象的声明
    // 它来设置上面JobDetail指定的任务的运行时机
    @Bean
    public Trigger showTimeTrigger() {
        System.out.println("++++++++++++++++++++");
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        return TriggerBuilder.newTrigger()
                // 设置要绑定的jobDetail对象
                .forJob(showTime())
                // 也要给当前触发器对象起名字
                .withIdentity("dateTrigger")
                // 设置绑定cron表达式
                .withSchedule(cron)
                .build();
    }

    /**
     * 增加库存
     *
     * @return
     */
    @Bean
    public JobDetail stockAdd() {
        System.out.println("开始增加库存---");
        // 利用JobBuilder类的newJob方法指定要运行的Job实现类的反射,我们编写的就是QuartzJob
        return JobBuilder.newJob(QuartzJob2.class)
                // 需要为当前任务起个名字,方便Quartz调用
                .withIdentity("stockAdd")
                // 默认情况下,JobDetail对象生成后,如果没有触发器绑定会被立即移除
                // 设置storeDurably方法后,当前JobDetail对象生成后,不会被移除了
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger stockAddTrigger() {
        System.out.println("开始增加库存++++Trigger+++++++");
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("0 0/2 * * * ?");
        return TriggerBuilder.newTrigger()
                // 设置要绑定的jobDetail对象
                .forJob(stockAdd())
                // 也要给当前触发器对象起名字
                .withIdentity("stockAddTrigger")
                // 设置绑定cron表达式
                .withSchedule(cron)
                .build();
    }

}
