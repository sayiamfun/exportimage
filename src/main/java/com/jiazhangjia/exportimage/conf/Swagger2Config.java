package com.jiazhangjia.exportimage.conf;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

//    @Bean
//    public Docket webApiConfig() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("WebApi")//组名
//                .apiInfo(webApiInfo())// 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
//                .select()//开启选择
//                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))//包含admin包下的所有接口方法
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))//排除error下的多所有接口方法
//                .build();
//    }
//    private ApiInfo webApiInfo() {
//        return new ApiInfoBuilder()
//                .title("web端用户中心微服务API")//大标题
//                .description("此文档描述了web端用户中心微服务API")//详细描述
//                .version("1.0")//版本
//                .contact(new Contact("李文杰", "http://test.com", "55317332@qq.com"))//作者
//                .build();
//    }

    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("exportimage")//组名
                .apiInfo(adminApiInfo())// 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()//开启选择
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//排除error下的多所有接口方法
                .build();
    }
    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("图片识别")//大标题
                .description("此文档描述了图片识别用到的所有服务接口")//详细描述
                .version("1.0")//版本
                .contact(new Contact("李文杰", "http://192.168.3.5:8080", "liwenjieemail@163.com"))//作者
                .build();
    }
}
