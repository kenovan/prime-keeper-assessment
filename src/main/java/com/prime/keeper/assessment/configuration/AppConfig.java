package com.prime.keeper.assessment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.prime.keeper.assessment.interceptor.AuthenticationInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan("com.prime.keeper.assessment")
public class AppConfig implements WebMvcConfigurer {

	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
	    return new AuthenticationInterceptor();
	}

	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor());
	}
}
