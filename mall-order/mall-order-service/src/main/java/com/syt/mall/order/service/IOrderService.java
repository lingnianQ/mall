package com.syt.mall.order.service;

import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import com.syt.mall.commons.pojo.order.model.Order;
import com.syt.mall.commons.restful.JsonPage;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:16
 */
public interface IOrderService {

    /**
     * 新增订单
     *
     * @param orderAddDTO 订单
     */
    void orderAdd(OrderAddDTO orderAddDTO);

    /**
     * 返回jsonPage类型
     *
     * @param page
     * @param pageSize
     * @return
     */
    JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize);
}
