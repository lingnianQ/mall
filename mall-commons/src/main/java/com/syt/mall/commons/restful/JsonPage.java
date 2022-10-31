package com.syt.mall.commons.restful;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/11/1 0:51
 */
@Data
public class JsonPage<T> implements Serializable {

    // 当前类应该和Page\PageInfo一样,既能包含分页查询结果,又包含分页信息
    // 分页信息方面,我们可以根据项目需求来声明
    // 当前只声明最基本的四个分页信息即可,今后可以随需求变化增加

    @ApiModelProperty(value = "总页数", name = "totalPages")
    private Integer totalPages;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private Integer totalCount;

    @ApiModelProperty(value = "页码", name = "page")
    private Integer page;

    @ApiModelProperty(value = "每页条数", name = "pageSize")
    private Integer pageSize;

    // JsonPage还要包含分页查询结果

    @ApiModelProperty(value = "分页数据", name = "list")
    private List<T> list;


}
