package com.liucm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/**")
	            .addResourceLocations("classpath:/META-INF/resources/")
	            .addResourceLocations("classpath:/resources/")
	            .addResourceLocations("classpath:/static/")
	            .addResourceLocations("classpath:/public/");
	    registry.addResourceHandler("/images/**").addResourceLocations(
	    		"file:///D:/videoweb/images/");
	    registry.addResourceHandler("/videos/**").addResourceLocations(
	    		"file:///D:/videoweb/videos/");
	    super.addResourceHandlers(registry);
	}
}

