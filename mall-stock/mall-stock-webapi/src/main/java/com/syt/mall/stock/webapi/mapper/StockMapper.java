package com.syt.mall.stock.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 库存
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 14:52
 */
@Repository
public interface StockMapper {

    /**
     * 修改(减少)商品库存
     *
     * @param commodityCode
     * @param reduceCount
     * @return
     */
    @Update("update stock_tbl " +
            " set count=count-#{reduceCount} " +
            " where commodity_code=#{commodityCode} " +
            " and count>=#{reduceCount}")
    int updateStockCount(@Param("commodityCode") String commodityCode,
                         @Param("reduceCount") Integer reduceCount);
}
