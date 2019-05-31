package com.weiyan.files.config;

import com.weiyan.files.interceptor.FileInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.config
 * @date 2019/5/31 17:49
 */
public class FileInterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private FileInterceptor fileInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List listPar = new ArrayList();
        listPar.add("/**/login/**");
        listPar.add("/**/index/**");
        listPar.add("/**/js/**");
        listPar.add("/**/css/**");
        listPar.add("/**/favicon.ico/**");
        listPar.add("/**/images/**");
        listPar.add("/static/**");
        listPar.add("classpath:/static/**");
        listPar.add("classpath:/templates/**");
        registry.addInterceptor(fileInterceptor).addPathPatterns("/**").excludePathPatterns(listPar);
   super.addInterceptors(registry);
    }
}
