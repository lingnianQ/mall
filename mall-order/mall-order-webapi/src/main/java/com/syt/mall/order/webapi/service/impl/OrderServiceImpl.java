package com.syt.mall.order.webapi.service.impl;

import com.syt.mall.cart.service.ICartService;
import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import com.syt.mall.commons.pojo.order.model.Order;
import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.commons.restful.ResponseCode;
import com.syt.mall.order.service.IOrderService;
import com.syt.mall.order.webapi.mapper.OrderMapper;
import com.syt.mall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * -@DubboService---order也是生产者,
 * 需要添加该注解--被business调用
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:16
 */
@DubboService
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 添加@DubboReference注解,表示当前业务逻辑层中需要消费其他模块的的服务
     * 声明的接口应该是其他服务提供的业务逻辑层接口
     * 因为Nacos注册中注册了业务的实现类,所以声明的接口回自动匹配到实现类对象
     * 我们需要两个模块的实现类,stock和cart
     */
    @DubboReference
    private IStockService stockService;

    @DubboReference
    private ICartService cartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        //TODO...1.减少订单中商品的库存数,(调用stock模块中的功能)
        //先实例化业务逻辑层需要的指定类型DTO才可以调用
        StockReduceCountDTO countDTO = new StockReduceCountDTO();
        countDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        countDTO.setReduceCount(orderAddDTO.getCount());
        //dubbo调用stock执行减少库存的方法
        stockService.reduceCommodityCount(countDTO);

        //TODO...2.从购物车中删除用户勾选的商品(调用cart模块的功能)
        cartService.deleteUserCart(orderAddDTO.getUserId(),
                orderAddDTO.getCommodityCode());

        if (Math.random() < 0.5) {
            throw new MallServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR, "发送随机异常"
            );
        }

        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO, order);
        int i = orderMapper.insertOrder(order);
        if (i != 1) {
            throw new MallServiceException(ResponseCode.CONFLICT, "新增订单失败");
        }
    }

}
