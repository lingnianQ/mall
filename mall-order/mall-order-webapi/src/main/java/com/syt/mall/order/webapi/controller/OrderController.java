package com.syt.mall.order.webapi.controller;

import com.github.pagehelper.PageInfo;
import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import com.syt.mall.commons.pojo.order.model.Order;
import com.syt.mall.commons.restful.JsonPage;
import com.syt.mall.commons.restful.JsonResult;
import com.syt.mall.order.service.IOrderService;
import com.syt.mall.order.webapi.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/base/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation("添加订单")
    @PostMapping("/add")
    public JsonResult orderAdd(OrderAddDTO orderAddDTO) {
        orderService.orderAdd(orderAddDTO);
        return JsonResult.ok("订单添加成功");
    }

    @GetMapping("/page")
    @ApiOperation("分页查询所有订单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "page", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "10")
    })
    public JsonResult<JsonPage<Order>> pageOrders(
            Integer page, Integer pageSize) {
        JsonPage<Order> jsonPage =
                orderService.getAllOrdersByPage(page, pageSize);
        return JsonResult.ok("查询完成", jsonPage);

    }
}
