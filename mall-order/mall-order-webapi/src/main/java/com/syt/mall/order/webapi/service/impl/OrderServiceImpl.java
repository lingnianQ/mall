package com.syt.mall.order.webapi.service.impl;

import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import com.syt.mall.commons.pojo.order.model.Order;
import com.syt.mall.commons.restful.ResponseCode;
import com.syt.mall.order.service.IOrderService;
import com.syt.mall.order.webapi.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:16
 */
@DubboService
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        //TODO...1.减少订单中商品的库存数,(调用stock模块中的功能)

        //TODO...2.从购物车中删除用户勾选的商品(调用cart模块的功能)

        
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO, order);
        int i = orderMapper.insertOrder(order);
        if (i != 1) {
            throw new MallServiceException(ResponseCode.CONFLICT, "新增订单失败");
        }
    }

}
