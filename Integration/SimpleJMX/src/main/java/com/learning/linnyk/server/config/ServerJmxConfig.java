package com.learning.linnyk.server.config;

import java.util.HashMap;
import java.util.Map;

import javax.management.MalformedObjectNameException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import com.learning.linnyk.server.beans.impl.MBeanExample;

@Configuration
@ComponentScan("com.learning.linnyk.server")
//@EnableMBeanExport //in order to use jmx without any configurations
public class ServerJmxConfig {

	@Bean
	public MBeanExporter mbeanExporter(final MBeanExample mBeanExample) {
		final Map<String, Object> beans = new HashMap<>();
		beans.put("beans:name=MBeanExample", mBeanExample);

		final MBeanExporter exporter = new MBeanExporter();
		exporter.setBeans(beans);
		return exporter;
	}

	@Bean
	public MBeanServerFactoryBean mbeanServer() {
		return new MBeanServerFactoryBean();
	}

	//------for remote connection
	@Bean
	public RmiRegistryFactoryBean registry() {
		final RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
		rmiRegistryFactoryBean.setPort(1099);
		return rmiRegistryFactoryBean;
	}

	@Bean
	@DependsOn("registry")
	public ConnectorServerFactoryBean connectorServer() throws MalformedObjectNameException {
		ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
		connectorServerFactoryBean.setObjectName("connector:name=rmi");
		connectorServerFactoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/connector");
		return connectorServerFactoryBean;
	}
	//------

}
