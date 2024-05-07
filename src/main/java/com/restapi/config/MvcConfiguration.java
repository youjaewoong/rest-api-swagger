package com.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
     
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
        .addResourceHandler("/swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
        
		registry.addResourceHandler("/**")
			.addResourceLocations("classpath:templates/", "classpath:static/")
			.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*");
	}
} 