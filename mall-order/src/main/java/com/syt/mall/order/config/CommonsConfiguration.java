package com.syt.mall.order.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 只有添加了@Configuration注解的类才能配置当前Spring的环境
 * 要扫描commons模块中的统一异常处理类所在的路径,否则异常处理功能不会生效
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/21 17:52
 */
@Configuration
@ComponentScan("com.syt.mall.commons.exception")
public class CommonsConfiguration {
}
