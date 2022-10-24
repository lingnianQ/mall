package com.syt.mall.cart.service;

import com.syt.mall.commons.pojo.cart.dto.CartAddDTO;

/**
 * 购物车业务接口
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 10:52
 */
public interface ICartService {
    /**
     * 新增购物车方法
     *
     * @param cartAddDTO
     */
    void cartAdd(CartAddDTO cartAddDTO);

    /**
     * 删除购物车中的商品
     *
     * @param userId
     * @param commodityCode
     */
    void deleteUserCart(String userId, String commodityCode);
}
