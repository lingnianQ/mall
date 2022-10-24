package com.syt.mall.cart.service.impl;

import com.syt.mall.cart.mapper.CartMapper;
import com.syt.mall.cart.service.ICartService;
import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.cart.dto.CartAddDTO;
import com.syt.mall.commons.pojo.cart.model.Cart;
import com.syt.mall.commons.restful.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购物车业务实现类
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 10:53
 */
@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartAddDTO, cart);
        int i = cartMapper.insertCart(cart);

        if (i != 1) {
            throw new MallServiceException(ResponseCode.CONFLICT, "添加失败");
        }

        log.info("购物车商品添加成功");
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        int i = cartMapper.deleteCartByUserIdAndCommodityCode(userId, commodityCode);
        if (i != 1) {
            throw new MallServiceException(ResponseCode.CONFLICT, "删除失败");
        }

        log.info("购物车商品删除成功");

    }
}
