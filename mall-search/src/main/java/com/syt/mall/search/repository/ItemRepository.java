package com.syt.mall.search.repository;

import com.syt.mall.search.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository是spring家族对框架持久层的规范命名
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/29 1:34
 */
@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
    /**
     * ItemRepository接口要继承SpringData框架提供的父接口ElasticsearchRepository
     * // 一旦继承,当前接口就可以编写连接ES操作数据的代码了,
     * // 继承了父接口后,SpringData会根据我们泛型中编写的Item找到对应的索引
     * // 会对这个索引自动生成基本的增删改查方法,我们自己无需再编写
     * // ElasticsearchRepository<[要操作的\关联的实体类名称],[实体类主键的类型]>
     * <p>
     * // SpringData实现自定义查询
     * // 我们要编写遵循SpringData给定格式的方法名
     * // SpringData会根据方法名称自动推断出查询目的,生成查询语句完成操作
     * // query(查询):表达当前方法是一个查询方法,等价于sql中的select
     * // Item/Items:确定要查询的实体类(对应的索引),不带s是查询单个对象的,带s的查集合
     * // By(通过/根据):标识开始设置查询条件的单词,等价于sql中的where
     * // Title:要查询的字段,可以是Item实体类中声明的任何字段
     * // Matches(匹配):执行查询条件的操作,Matches是匹配字符串类型的关键字,支持分词等价sql中的like
     */

    /**
     * 按标题匹配查询项目
     *
     * @param title
     * @return
     */
    Iterable<Item> queryItemsByTitleMatches(String title);

    /**
     * // 多条件查询
     * // 多个条件之间要使用and或or来分隔,表示多个条件间的逻辑关系
     * // 如果我们想按照Title和Brand进行多条件查询
     * // 多个条件时,方法名就要按规则编写多个条件,参数也要对应条件的个数来变化
     * // 声明的参数会按照顺序赋值给方法名中的条件,和参数名称无关
     */
    /**
     * 按标题匹配和品牌匹配查询项目
     *
     * @param title
     * @param brand
     * @return
     */
    Iterable<Item> queryItemsByTitleMatchesAndBrandMatches(
            String title, String brand);

    /**
     * 排序查询
     *
     * @param title
     * @param brand
     * @return
     */
    Iterable<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(
            String title, String brand);

    /**
     * // 分页查询
     * // 返回值类型需要修改为Page类型,这个类型既可以保存从ES中查询到的数据又可以保存
     * // 当前分页查询的分页信息:当前页,总页数,总条数,每页条数,有没有上一页,有没有下一页等
     * // 分页查询方法参数要添加一个Pageable,必须放在现有所有参数的后面
     * // 它可以设置要查询的页码和每页的条数
     *
     * @param title
     * @param brand
     * @param pageable
     * @return
     */
    Page<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(
            String title, String brand, Pageable pageable);
}
