package com.syt.mall.stock.webapi.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.commons.restful.JsonResult;
import com.syt.mall.stock.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存管理
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:57
 */
@Api(tags = "库存管理")
@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    /**
     * -@SentinelResource注解需要写到控制层方法上
     * 在该方法运行后,会被Sentinel监测
     * 该方法运行前,Sentinel检测不到,至少必须运行一次后才可以
     * "减少商品库存数"-就是显示在Sentinel上的名称
     *
     * @param stockReduceCountDTO
     * @return
     */
    @ApiOperation("减少库存")
    @PostMapping("/reduce/count")
    @SentinelResource("减少商品库存数")
    public JsonResult stockReduce(StockReduceCountDTO stockReduceCountDTO) {
        stockService.reduceCommodityCount(stockReduceCountDTO);
        return JsonResult.ok("库存减少成功");
    }
}
