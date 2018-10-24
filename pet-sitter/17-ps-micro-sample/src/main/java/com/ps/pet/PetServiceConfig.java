package com.ps.pet;

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
@EntityScan("com.ps.pet")
@EnableJpaRepositories("com.ps.pet")
@PropertySource("classpath:db/pet/db.properties")
public class PetServiceConfig {

	@Bean
	public DataSource dataSource() {
		log.debug("creating database");
		final DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:db/pet/schema.sql")
				.addScript("classpath:db/pet/data.sql")
				.build();

		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		final List<Map<String, Object>> pets = jdbcTemplate.queryForList("SELECT id FROM P_PET");

		log.info("System has " + pets.size() + " pets");

		return dataSource;
	}
}
