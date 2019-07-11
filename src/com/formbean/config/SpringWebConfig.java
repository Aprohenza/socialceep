package com.formbean.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.formbean.session.SessionManager;
import com.formbean.session.UserFriendsRequestSession;
import com.formbean.session.UserOwnPostSession;
import com.formbean.session.UserOwnPostSessionLoad;
import com.formbean.session.UserSession;
import com.formbean.updates.CheckNewPost;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.formbean.controller", "com.formbean.form", "com.formbean.validator", "com.formbean.entity", "com.formbean.session" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/assets/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("/WEB-INF/messages");
		return source;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(500000000);
		return multipartResolver;
	}
	
	/*
	 * @Bean public SessionManager sManager() { return new SessionManager(); }
	 * 
	 * @Bean public UserSession uSession() { return new UserSession(); }
	 */
	
	
	/*
	 * @Bean public UserOwnPostSession uOwnPostSession() { return new
	 * UserOwnPostSession(); }
	 */
	
	/*
	 * @Bean public UserFriendsRequestSession userFriendsRequestSession() { return
	 * new UserFriendsRequestSession(); }
	 */
	
	/*
	 * @Bean public UserOwnPostSessionLoad userOwnPostSessionLoad() { return new
	 * UserOwnPostSessionLoad(); }
	 */
	
	/*
	 * @Bean public CheckNewPost checkNewPost() { return new CheckNewPost(); }
	 */

}
