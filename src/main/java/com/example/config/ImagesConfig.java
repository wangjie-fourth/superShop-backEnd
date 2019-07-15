package com.example.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 通过url来访问本地图片资源
 * Created by wangjie_fourth on 2019/5/16.
 */
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "fileImages")
@Data
public class ImagesConfig extends WebMvcConfigurerAdapter {

    private String readPath;

    private String writePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(readPath).addResourceLocations("file:"+writePath);
        super.addResourceHandlers(registry);
    }
}
