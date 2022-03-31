package ${package}.init;

import ${package}.web.interceptor.AppAuthInterceptor;
import ${package}.web.interceptor.LoggerInterceptor;
import ${package}.web.interceptor.ManageAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: spring mvc配置
 * @author: liliwen
 * @date: 2018-11-15
 */
@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 权限拦截器:
     * 因为拦截器需要注入service, 且在spring context之前加载
     */
    /**管理端拦截器*/
    @Bean
    public ManageAuthInterceptor manageAuthInterceptor() {
        return new ManageAuthInterceptor();
    }
    /**应用端拦截器*/
    @Bean
    public AppAuthInterceptor appAuthInterceptor() {
        return new AppAuthInterceptor();
    }

    /**跨域处理*/
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        Map<String, CorsConfiguration> map = new HashMap<>();
        map.put("/manage/**", config);
        map.put("/open/**", config);
        map.put("/app/**", config);
        map.put("/static/**", config);
        configSource.setCorsConfigurations(map);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //日志拦截器
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/**");
        //管理端权限拦截器
        registry.addInterceptor(manageAuthInterceptor())
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/manage/admin/login");
        //应用端权限拦截器
        registry.addInterceptor(appAuthInterceptor())
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/user/token");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态目录
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        //swagger
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }

}
