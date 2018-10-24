package com.ps.user;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ComponentScan
@EntityScan("com.ps.user")
@EnableJpaRepositories("com.ps.user")
@PropertySource("classpath:db/user/db.properties")
public class UserServiceConfig {

	@Bean
	public DataSource dataSource() {
		log.debug("creating database");
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:db/user/schema.sql")
				.addScript("classpath:db/user/data.sql").build();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT id FROM P_USER");

		log.info("System has {} users", users.size());
		return dataSource;
	}

}
