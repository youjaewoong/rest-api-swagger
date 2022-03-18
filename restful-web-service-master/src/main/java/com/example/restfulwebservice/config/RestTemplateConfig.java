package com.example.restfulwebservice.config;

import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean("closeableHttpClient")
	public CloseableHttpClient closeableHttpClient() {
		CloseableHttpClient httpClient = null;
		try {
			TrustStrategy trustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			};
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
			httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpClient;
	}
	
	
	@Bean("requestFactory")
	public HttpComponentsClientHttpRequestFactory requestFactory() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(closeableHttpClient());
		requestFactory.setConnectionRequestTimeout(1000*10);
		requestFactory.setConnectTimeout(1000*10);
		return requestFactory;
	}
	
	
	@Bean("customRestTemplate")
	public RestTemplate customRestTemplate() {
		MappingJackson2CborHttpMessageConverter mappingJackson2CborHttpMessageConverter = new MappingJackson2CborHttpMessageConverter();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		converters.add(mappingJackson2CborHttpMessageConverter);
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate(requestFactory());
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
}
