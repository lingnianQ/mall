package com.syt.mall.stock.service;

import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;

/**
 * 库存的业务逻辑层
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:53
 */
public interface IStockService {
    /**
     * 减少库存
     *
     * @param stockReduceCountDTO
     */
    void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO);
}
