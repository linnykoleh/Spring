package livelessons;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		// Disabling cache control example, should be Enabled(commented), to give Spring control the cache by itself
		http.headers()
				.cacheControl().disable();

		http.authorizeRequests().and().formLogin().permitAll();
		http.logout().permitAll();
	}

}
