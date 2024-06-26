package com.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//이름/URL/EMAIL
    private static final Contact DEFAULT_CONTACT = new Contact("j.w","https://break-over.tistory.com","n_nv711@naver.com");

    //타이틀/설명/버전/서비스정보/회원정보/라이센스/라이센스주소
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Example API",
            " REST API Example", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            //Arrays.asList("application/json", "application/xml"));
    		Arrays.asList("application/json"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
             //   .produces(DEFAULT_PRODUCES_AND_CONSUMES)
             //   .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .ignoredParameterTypes(HttpSession.class);  // HttpSession 타입 제외
    }

}
