package com.spring4.mvc.linnyk.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		final WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.html");
		dispatcher.addMapping("*.pdf");
		dispatcher.addMapping("*.json");
	}

	private AnnotationConfigWebApplicationContext getContext(){
		final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.register(WebConfig.class);
		return annotationConfigWebApplicationContext;
	}
}
