package com.weiyan.files.config;

import com.weiyan.files.interceptor.FileInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.config
 * @date 2019/5/31 9:21
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig  extends WebMvcConfigurationSupport {
    @Autowired
    private FileInterceptor fileInterceptor;
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List listPar = new ArrayList();
        listPar.add("/static/**");
        List interceptorList = new ArrayList();
        interceptorList.add("/**");
        registry.addInterceptor(fileInterceptor).addPathPatterns(interceptorList).excludePathPatterns(listPar);
    }
}
