package ${package}.init;

import com.fasterxml.classmate.TypeResolver;
import ${package}.web.doc.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger配置
 * @author: liliwen
 * @date: 2018-11-28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;
    @Autowired
    private TypeResolver typeResolver;

    /**公共接口*/
    @Bean
    public Docket publicApi() {
        return new Docket(DocumentationType.OAS_30)
                .additionalModels(typeResolver.resolve(StatusCode.class))
                .groupName("open-api")
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("${package}.web.controller.open"))
                .paths(PathSelectors.any())
                .build();
    }

    /**管理端接口*/
    @Bean
    public Docket manageApi() {
        return new Docket(DocumentationType.OAS_30)
                .additionalModels(typeResolver.resolve(StatusCode.class))
                .groupName("manage-api")
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("${package}.web.controller.manage"))
                .paths(PathSelectors.any())
                .build();
    }

    /**应用端接口*/
    @Bean
    public Docket gameApi() {
        return new Docket(DocumentationType.OAS_30)
                .additionalModels(typeResolver.resolve(StatusCode.class))
                .groupName("app-api")
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("${package}.web.controller.app"))
                .paths(PathSelectors.any())
                .build();
    }

    /**api信息*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api")
                .version("1.0.0")
                .build();
    }

}
