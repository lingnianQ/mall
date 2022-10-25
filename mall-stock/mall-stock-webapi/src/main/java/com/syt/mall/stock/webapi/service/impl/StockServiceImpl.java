package com.syt.mall.stock.webapi.service.impl;

import com.syt.mall.commons.exception.MallServiceException;
import com.syt.mall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.syt.mall.commons.restful.ResponseCode;
import com.syt.mall.stock.service.IStockService;
import com.syt.mall.stock.webapi.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:53
 */
@Slf4j
@Service
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;

    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        int i = stockMapper.updateStockCount(stockReduceCountDTO.getCommodityCode(),
                stockReduceCountDTO.getReduceCount());

        if (i != 1) {
            throw new MallServiceException(ResponseCode.CONFLICT, "减少库存失败");
        }
    }
}
