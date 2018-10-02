package com.demo.config;

import org.springframework.web.servlet.support.*;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Go to com.demo.config.SecurityWebAppInitializer.SecurityWebAppInitializer to register RootConfig
		// return null in order not to create two contexts
		return null;
	}

}
