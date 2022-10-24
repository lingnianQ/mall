package com.syt.mall.cart.controller;

import com.syt.mall.cart.service.ICartService;
import com.syt.mall.commons.pojo.cart.dto.CartAddDTO;
import com.syt.mall.commons.restful.JsonResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车控制层
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 11:01
 */
@Api(tags = "购物车管理")
@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @ApiOperation("添加购物车商品")
    @PostMapping("/add")
    public JsonResult cartAdd(CartAddDTO cartAddDTO) {
        cartService.cartAdd(cartAddDTO);
        return JsonResult.ok("添加成功");
    }

    @ApiOperation("删除购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID", name = "userId", example = "UU100"),
            @ApiImplicitParam(value = "商品编号", name = "commodityCode", example = "PC100")
    })
    @PostMapping("/delete")
    public JsonResult deleteUserCart(String userId, String commodityCode) {
        cartService.deleteUserCart(userId, commodityCode);
        return JsonResult.ok("删除成功");
    }
}
