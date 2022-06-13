package com.learning.springboot.config;

import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
//http://localhost:8080/swagger-ui/index.html
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        //header
//        ParameterBuilder builder = new ParameterBuilder();
//        builder.parameterType("header").name("token")
//                .description("token值")
//                .required(true)
//                .modelRef(new ModelRef("string")); // 在swagger里显示header
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("aitest_interface")
//                .apiInfo(apiInfo())
//                .globalOperationParameters(Lists.newArrayList(builder.build()))
//                .select().paths(PathSelectors.any()).build();

        RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
        tokenBuilder
                .in("header")
                .name("token")
                .description("token value")
                .required(true)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("aitest_interface")
                .apiInfo(apiInfo())
                .globalRequestParameters(Lists.newArrayList(tokenBuilder.build()))
                .select().paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        //apiInfo
        return new ApiInfoBuilder()
                .title("aitest-interface system")
                .description("aitest-interface document")
                .contact(new Contact("tlibn","","@gmail.com"))
                .version("1.0")
                .build();
    }
}
