package com.syt.mall.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syt.mall.business.sevice.IBusinessService;
import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.commons.restful.JsonResult;
import com.syt.mall.commons.restful.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/21 17:57
 */
@RestController
@RequestMapping("/base/business")
@Api(tags = "业务触发模块")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;

    /**
     * localhost:20000/base/business/buy
     * 因为代码设定的请求方式是Post,所以不能使用浏览器输入地址栏的方式测试,必须用knife4j
     *
     * @return JsonResult
     */
    @PostMapping("/buy")
    @ApiOperation("执行业务的触发")
    @SentinelResource(value = "business业务",
            blockHandler = "businessBlock",
            fallback = "businessFallback")
    public JsonResult buy() {
        // 调用业务逻辑层方法
        businessService.buy();
        // 随机发生异常,测试服务降级
//        if (Math.random() < 0.5) {
//            throw new MallServiceException(
//                    //发生该异常,会降级
//                    ResponseCode.INTERNAL_SERVER_ERROR, "随机异常"
//            );
//        }
        return JsonResult.ok("购买完成!");
    }

    /**
     * 限流方法
     *
     * @param e
     * @return
     */
    public JsonResult businessBlock(BlockException e) {
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "服务器忙,请稍后再试");
    }

    /**
     * 降级方法
     * 当控制器发生异常时,Sentinel会调用这个方法,优先级比比统一异常处理类高
     * 实际开发中,可能包含一些业务::运行老版本代码,或使用户获得一些补偿
     *
     * @param throwable
     * @return
     */
    public JsonResult businessFallback(Throwable throwable) {
        throwable.printStackTrace();
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "运行发生异常,服务降级!");
    }

}
