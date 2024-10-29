package com.ric.scaffold.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * swagger 配置
 */
@Configuration
public class SwaggerConfig {

  /**
   * 文档信息
   * @return
   */
  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("SpringBoot 开发脚手架")
        .description("SpringBoot 快速开发脚手架")
        .version("1.0.0")
        .build();
  }

  @Bean
  public Docket userApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("OpenAPI模块")
        .apiInfo(openApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ric.scaffold.api.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public Docket orderApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("内部接口模块")
        .apiInfo(intenalApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ric.scaffold.web.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo openApiInfo() {
    return new ApiInfoBuilder()
        .title("OpenAPI模块")
        .description("开放接口的描述")
        .version("1.0")
        .build();
  }

  private ApiInfo intenalApiInfo() {
    return new ApiInfoBuilder()
        .title("内部接口模块")
        .description("系统内相关接口的描述")
        .version("1.0")
        .build();
  }
}
