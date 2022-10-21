package com.syt.mall.business.controller;

import com.syt.mall.business.sevice.IBusinessService;
import com.syt.mall.commons.restful.JsonResult;
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
    public JsonResult buy() {
        // 调用业务逻辑层方法
        businessService.buy();
        return JsonResult.ok("购买完成!");
    }
}
