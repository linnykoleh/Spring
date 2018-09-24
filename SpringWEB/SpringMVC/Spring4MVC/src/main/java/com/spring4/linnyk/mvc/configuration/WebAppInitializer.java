package com.spring4.linnyk.mvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
		final WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));

		final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.html");
		dispatcher.addMapping("*.pdf");
		dispatcher.addMapping("*.json");

		dispatcher.setInitParameter("contextConfigLocation", "com.spring4.application.configuration.ApplicationConfig");
		dispatcher.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
	}

	private AnnotationConfigWebApplicationContext getContext(){
		final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.register(WebConfig.class);
		return annotationConfigWebApplicationContext;
	}
}
