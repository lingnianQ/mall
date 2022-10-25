package com.syt.mall.business.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j（Swagger2）的配置
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/21 17:56
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    /**
     * 【重要】指定Controller包路径
     */
    private final String basePackage = "com.syt.mall.business.controller";
    /**
     * 分组名称
     */
    private final String groupName = "base-business";
    /**
     * 主机名
     */
    private final String host = "http://java.tedu.cn";
    /**
     * 标题
     */
    private final String title = "商城项目案例在线API文档--基础businessr-web实例";
    /**
     * 简介
     */
    private final String description = "构建基础business-web项目,实现购买";
    /**
     * 服务条款URL
     */
    private final String termsOfServiceUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private final String contactName = "项目研发部";
    /**
     * 联系网址
     */
    private final String contactUrl = "http://java.tedu.cn";
    /**
     * 联系邮箱
     */
    private final String contactEmail = "java@tedu.cn";
    /**
     * 版本号
     */
    private final String version = "1.0-SNAPSHOT";

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket docket() {
        String groupName = "1.0-SNAPSHOT";
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version(version)
                .build();
    }

}
