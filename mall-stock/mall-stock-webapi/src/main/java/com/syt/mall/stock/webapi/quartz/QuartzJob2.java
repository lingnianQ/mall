package com.syt.mall.stock.webapi.quartz;

import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.stock.service.IStockService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/11 21:00
 */
public class QuartzJob2 implements Job {

    @Autowired
    private IStockService stockService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //quartz任务演示
        System.out.println("Quartz(添加库存---)任务演示, 当前时间: " + LocalDateTime.now() + "-------------");
        StockReduceCountDTO stockReduceCountDTO = new StockReduceCountDTO();
        stockReduceCountDTO.setReduceCount(-10);
        stockReduceCountDTO.setCommodityCode("PC100");
        stockService.reduceCommodityCount(stockReduceCountDTO);
    }
}
