package com.learning.linnyk.client.config;

import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import com.learning.linnyk.server.beans.IMBeanExample;

@Configuration
public class ClientJmxConfig {

	@Bean
	public MBeanServerConnectionFactoryBean connectionFactoryBean() throws MalformedURLException {
		final MBeanServerConnectionFactoryBean mbscfb = new MBeanServerConnectionFactoryBean();
		mbscfb.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/connector");
		return mbscfb;
	}

	@Bean
	public MBeanProxyFactoryBean mBeanProxyFactoryBean(MBeanServerConnection connection) throws MalformedObjectNameException {
		final MBeanProxyFactoryBean schedulerProxy = new MBeanProxyFactoryBean();
		schedulerProxy.setObjectName("beans:name=MBeanExample");
		schedulerProxy.setServer(connection);
		schedulerProxy.setManagementInterface(IMBeanExample.class);
		return schedulerProxy;
	}

}
