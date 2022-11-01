package com.syt.mall.order.webapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syt.mall.cart.service.ICartService;
import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import com.syt.mall.commons.pojo.order.model.Order;
import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.commons.restful.JsonPage;
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

import java.util.List;

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

//        if (Math.random() < 0.5) {
//            throw new MallServiceException(
//                    ResponseCode.INTERNAL_SERVER_ERROR, "发送随机异常"
//            );
//        }

        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO, order);
        int i = orderMapper.insertOrder(order);
        if (i != 1) {
            throw new MallServiceException(ResponseCode.CONFLICT, "新增订单失败");
        }
    }

    /**
     * 分页查询所有订单信息的方法
     * 参数page是页码,pageSize是每页条数
     *
     * @param page
     * @param pageSize
     * @return new PageInfo<>(list)
     */
    @Override
    public JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize) {
        // PageHelper框架实现分页功能最核心的代码,是要编写在执行查询数据库代码之前的
        // PageHelper.startPage方法就是在设置分页的查询条件
        // page是查询的页码(从1开始),pageSize是每页条数
        PageHelper.startPage(page, pageSize);
        // 上面设置好分页查询条件,下面的查询在执行时,sql语句会自动被追加limit关键字
        List<Order> list = orderMapper.findAllOrders();

        // list变量并不是全查结果,而是只包含指定页码内容的数据
        // 我们分页业务功能不能只返回分页查询结果,还需要提供分页信息
        // PageHelper框架提供了PageInfo类,既能保存分页结果,也能保存分页信息
        // 分页信息无需我们计算,直接实例化PageInfo对象,它会自动根据上面的查询生成
        return JsonPage.restPage(new PageInfo<>(list));
    }

}
