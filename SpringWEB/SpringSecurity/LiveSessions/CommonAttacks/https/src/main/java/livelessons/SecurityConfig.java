package livelessons;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		http.authorizeRequests().and().formLogin().permitAll();
		http.logout().permitAll();

		// Enabling/Disabling HTTPS
		http
			.headers()
				.httpStrictTransportSecurity().disable() // Not recommended
			.and()
			.requiresChannel()
				.requestMatchers(r -> r.getHeader("x-forwarded-proto") != null)
				.requiresSecure();


	}

}
