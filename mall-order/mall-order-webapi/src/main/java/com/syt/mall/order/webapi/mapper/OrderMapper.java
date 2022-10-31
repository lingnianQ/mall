package com.syt.mall.order.webapi.mapper;

import com.syt.mall.commons.pojo.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * PageHelper框架完成分页功能的原理是sql语句后自动添加limit
     * 所以我们在编写查询方法时,无需关注分页操作,和普通查询没有区别
     *
     * @return list
     */
    @Select("select id,user_id,commodity_code,count,money from order_tbl")
    List<Order> findAllOrders();

}
