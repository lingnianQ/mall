package com.syt.mall.business.sevice.impl;

import com.syt.mall.business.sevice.IBusinessService;
import com.syt.mall.commons.pojo.order.dto.OrderAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/21 17:54
 */
@Slf4j
@Service
public class BusinessServiceImpl implements IBusinessService {
    @Override
    public void buy() {
        // 模拟购买业务
        // 实例化一个用于新增订单的DTO对象
        OrderAddDTO orderAddDTO = new OrderAddDTO();
        // 为属性赋值
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setCount(3);
        orderAddDTO.setMoney(500);
        // 因为是模拟购买,还没有操作数据库的条件,所以要输出即可
        log.info("新增订单信息为:{}", orderAddDTO);
    }
}