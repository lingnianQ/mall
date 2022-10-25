package com.syt.mall.order.webapi.controller;

import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import com.syt.mall.commons.restful.JsonResult;
import com.syt.mall.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:15
 */
@Slf4j
@Api(tags = "订单管理模块")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation("添加订单")
    @PostMapping("/add")
    public JsonResult orderAdd(OrderAddDTO orderAddDTO) {
        orderService.orderAdd(orderAddDTO);
        return JsonResult.ok("订单添加成功");
    }

}
