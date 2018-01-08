package com.essilor.spring.reactive.app.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.essilor.spring.reactive.app.handler.ObservableReturnValueHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.essilor.spring.reactive.app.controller.*"})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Resource
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
	@PostConstruct
	public void init() {
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList(requestMappingHandlerAdapter.getReturnValueHandlers());
		handlers.add(0, observableReturnValueHandler());
		requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
	}
	
	@Bean
    public HandlerMethodReturnValueHandler observableReturnValueHandler() {
        return new ObservableReturnValueHandler();
    }


}
