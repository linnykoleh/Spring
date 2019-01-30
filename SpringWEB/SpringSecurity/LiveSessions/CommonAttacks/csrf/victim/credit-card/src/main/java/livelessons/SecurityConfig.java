package livelessons;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http
			.csrf()
				.ignoringAntMatchers("/login");
	}

	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		UserDetails user1 = User.withUserDetails(user)
				.username("use1")
				.build();
		return new InMemoryUserDetailsManager(user, user1);
	}
}
