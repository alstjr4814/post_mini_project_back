package com.korit.post_mini_project_back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${user.dir}")
    private String projectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String uploadPath = projectPath + "/upload";
        registry.addResourceHandler("/image/**")                //외부에서 localhost:8080/image/** 의 요청이 들어오면
                .addResourceLocations("file:///" + uploadPath)  //스프링 부트 서버 PC의 프로젝트 폴더 안의 upload폴더로
                .resourceChain(true)                            //연결
                .addResolver(new PathResourceResolver() {       //요청 URL에 한글이 들어있을 수 있으니 한글 디코딩
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
                        return super.getResource(resourcePath, location);
                    }
                });
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}