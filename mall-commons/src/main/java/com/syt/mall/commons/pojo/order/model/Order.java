package com.syt.mall.commons.pojo.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sytsn
 */
@Data
public class Order implements Serializable {

    private Integer id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;

}
