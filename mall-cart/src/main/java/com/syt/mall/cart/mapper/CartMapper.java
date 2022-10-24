package com.syt.mall.cart.mapper;

import com.syt.mall.commons.pojo.cart.model.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 购物车
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 10:36
 */
@Repository
public interface CartMapper {
    /**
     * 新增购物车商品的方法
     *
     * @param cart 购物车实体
     * @return 受影响的行
     */
    @Insert("INSERT INTO cart_tbl(commodity_code,price,count,user_id)" +
            " values(#{commodityCOde},#{price},#{count},#{userId})")
    int insertCart(Cart cart);

    /**
     * 删除购物车商品
     *
     * @param userId 用户id
     * @param commodityCode 商品编号
     * @return 行数
     */
    @Delete("delete from cart_tbl where user_id=#{userId} and " +
            "commodity_code=#{commodityCode}")
    int deleteCartByUserIdAndCommodityCode(
            @Param("userId") String userId,
            @Param("commodityCode") String commodityCode);
}
