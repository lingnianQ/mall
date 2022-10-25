package com.syt.mall.stock.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisConfiguration
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/24 10:28
 */
@Configuration
@MapperScan("com.syt.mall.stock.webapi.mapper")
public class MybatisConfiguration {
}
