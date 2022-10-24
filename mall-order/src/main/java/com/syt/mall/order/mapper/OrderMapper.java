package com.syt.mall.order.mapper;

import com.syt.mall.commons.pojo.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:15
 */
@Repository
public interface OrderMapper {
    /**
     * 新增订单
     *
     * @param order 订单
     * @return 行数
     */
    @Insert("insert into order_tbl(user_id,commodity_code,count,money)" +
            " values(#{userId},#{commodityCode},#{count},#{money})")
    int insertOrder(Order order);
}
