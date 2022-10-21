package com.syt.mall.commons.pojo.stock.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sytsn
 */
@Data
public class Stock implements Serializable {

    private Integer id;
    private String commodityCode;
    private Integer reduceCount;
}






