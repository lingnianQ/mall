package com.syt.mall.stock.webapi.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.commons.restful.JsonResult;
import com.syt.mall.commons.restful.ResponseCode;
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
@RequestMapping("/base/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    /**
     * -@SentinelResource注解需要写到控制层方法上
     * 在该方法运行后,会被Sentinel监测
     * 该方法运行前,Sentinel检测不到,至少必须运行一次后才可以
     * "减少商品库存数"-就是显示在Sentinel上的名称--value
     *
     * @param stockReduceCountDTO
     * @return
     */
    @ApiOperation("减少库存")
    @PostMapping("/reduce/count")
    @SentinelResource(value = "减少商品库存数",
            blockHandler = "blockError",
            fallback = "fallbackError")
    public JsonResult stockReduce(StockReduceCountDTO stockReduceCountDTO) {
        stockService.reduceCommodityCount(stockReduceCountDTO);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // 随机发生异常,测试服务降级
//        if (Math.random() < 0.5) {
//            throw new MallServiceException(
//                    //发生该异常,会降级
//                    ResponseCode.INTERNAL_SERVER_ERROR, "随机异常"
//            );
//        }

        return JsonResult.ok("库存减少成功");
    }

    /**
     * 限流方法
     *
     * @param stockReduceCountDTO
     * @param e
     * @return
     */
    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO,
                                 BlockException e) {
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "服务器忙,请稍后再试");
    }

    /**
     * 降级方法
     * 当控制器发生异常时,Sentinel会调用这个方法,优先级比比统一异常处理类高
     * 实际开发中,可能包含一些业务::运行老版本代码,或使用户获得一些补偿
     *
     * @param stockReduceCountDTO
     * @param throwable
     * @return
     */
    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO,
                                    Throwable throwable) {
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "运行发生异常,服务降级!");
    }
}
