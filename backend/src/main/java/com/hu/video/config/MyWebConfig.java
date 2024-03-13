package com.hu.video.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      修改为自己的电脑绝对路径
        registry.addResourceHandler("/static/video/**").addResourceLocations("file:C:\\Users\\XWW\\Desktop\\VideoSystem-master\\backend\\src\\main\\resources\\static\\video\\");
        registry.addResourceHandler("/static/uimages/**").addResourceLocations("file:C:\\Users\\XWW\\Desktop\\VideoSystem-master\\backend\\src\\main\\resources\\static\\uimages\\");
    }
}
//C:\Users\XWW\Desktop\VideoSystem-master\backend\target\classes\static\video