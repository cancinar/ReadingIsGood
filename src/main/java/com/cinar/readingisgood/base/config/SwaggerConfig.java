package com.cinar.readingisgood.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


  @Bean
  public Docket bookApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("BOOK").select()
        .apis(RequestHandlerSelectors.basePackage("com.cinar.readingisgood.book.view.controller")).build();
  }

  @Bean
  public Docket orderApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("ORDER").select()
        .apis(RequestHandlerSelectors.basePackage("com.cinar.readingisgood.order.view.controller")).build();
  }

  @Bean
  public Docket authApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("AUTH").select()
        .apis(RequestHandlerSelectors.basePackage("com.cinar.readingisgood.auth.view")).build();
  }


  @Bean
  public Docket customerApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("CUSTOMER").select()
        .apis(RequestHandlerSelectors.basePackage("com.cinar.readingisgood.customer.view.controller")).build();
  }


  @Bean
  public Docket statisticsApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("STATISTICS").select()
        .apis(RequestHandlerSelectors.basePackage("com.cinar.readingisgood.statistics.view.controller")).build();
  }



}
