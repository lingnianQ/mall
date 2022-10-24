package com.syt.mall.cart.service.impl;

import com.syt.mall.cart.mapper.CartMapper;
import com.syt.mall.cart.service.ICartService;
import com.syt.mall.commons.pojo.cart.dto.CartAddDTO;
import com.syt.mall.commons.pojo.cart.model.Cart;
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
        cartAddDTO.setUserId("UU200");
        cartAddDTO.setCommodityCode("PU300");
        cartAddDTO.setCount(10);
        cartAddDTO.setPrice(300);
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartAddDTO, cart);
        int i = cartMapper.insertCart(cart);

    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        int i = cartMapper.deleteCartByUserIdAndCommodityCode(userId, commodityCode);
    }
}
