package com.example.restfulwebservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageInterceptor;

@Configuration
@MapperScan(value = {"com.example.restfulwebservice.*"})
public class MyBatisConfig {

  /**
   * PageHelper 적용
   */
  @Bean
  ConfigurationCustomizer mybatisConfigurationCustomizer() {
    return new ConfigurationCustomizer() {
      @Override
      public void customize(org.apache.ibatis.session.Configuration configuration) {
        configuration.addInterceptor(new PageInterceptor());
      }
    };
  }
}