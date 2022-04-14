package com.volunteer.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/10 13:48
 */


@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

    @Autowired(required = false)
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    @ConditionalOnMissingBean
    public Docket apiDocket2() {
        // 默认组名, 文档更具组名查询,不同组名不能聚合
        String groupName = "default";
        return new Docket(DocumentationType.SWAGGER_2)
                // 文档信息
                .apiInfo(apiInfo())
                // 组名
                .groupName(groupName)
                .select()
                // 指定Controller扫描包路径(如下面第一个apis方法)     因Controller类上要添加Api注解, 所以可通过指定类上注解
                // .apis(RequestHandlerSelectors.basePackage("com.github.xiaoymin.knife4j.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                // 赋予插件体系
                .extensions(getExtensions(groupName));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("高校志愿者系统")
                .contact(new Contact("王班","localhost:2333","xxx@gmail.com"))
                .description("基于springboot的人生旅途记录系统系统接口文档")
                .termsOfServiceUrl("localhost")
                .version("1.0.0")
                .build();
    }

    /**
     * 根据组名添加插件
     */
    private List<VendorExtension> getExtensions(String groupName) {
        return null != openApiExtensionResolver ? openApiExtensionResolver
                .buildExtensions(groupName) : new ArrayList<>();
    }
}
