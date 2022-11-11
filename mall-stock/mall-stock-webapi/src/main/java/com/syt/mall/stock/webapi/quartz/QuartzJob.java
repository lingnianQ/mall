package com.syt.mall.stock.webapi.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/11 21:00
 */
public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //quartz任务演示
        System.out.println("Quartz任务演示, 当前时间: " + LocalDateTime.now() + "-------------");
    }
}
