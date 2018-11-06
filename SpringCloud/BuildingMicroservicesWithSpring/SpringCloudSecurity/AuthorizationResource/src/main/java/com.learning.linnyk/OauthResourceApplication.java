package com.learning.linnyk;

import java.security.Principal;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class OauthResourceApplication {

	@Bean
	public RemoteTokenServices remoteTokenServices() {
		final RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
		tokenServices.setClientId("resource1");
		tokenServices.setClientSecret("secret");
		return tokenServices;
	}

	@RequestMapping("/resource/endpoint")
	@PreAuthorize("hasRole('ADMIN')")
	public String endpoint(Principal principal) {
		return "Welcome, " + principal.getName() + ", this message is protected by the resource server";
	}

	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource());
	}

	@Bean
	public DataSource dataSource(){
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/sample");
		dataSource.setUsername("olinnyk");
		dataSource.setPassword("sample");
		return dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthResourceApplication.class, args);
	}
}

