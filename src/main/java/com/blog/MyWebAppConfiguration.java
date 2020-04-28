package com.blog;

/**
 * @description: 图片地址映射
 * @program: blog
 * @author: 284668461@qq.com
 * @create: 2020-04-26 10:46
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 添加一些虚拟路径的映射
     * 静态资源路径和上传文件的路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置, 创建一个虚拟路径/Path/** ，即只要在< img src="/Path/img/picName.jpg" />
         *便可以直接引用图片
         *这是图片的物理路径  "file:/+本地图片的地址"
         */
        registry.addResourceHandler("/Path/img/**").addResourceLocations("file:/D:/javaProject/blog/src/main/resources/static/files/");

        super.addResourceHandlers(registry);
    }
}