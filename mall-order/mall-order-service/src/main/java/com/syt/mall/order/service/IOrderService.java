package com.syt.mall.order.service;

import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;

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
}
