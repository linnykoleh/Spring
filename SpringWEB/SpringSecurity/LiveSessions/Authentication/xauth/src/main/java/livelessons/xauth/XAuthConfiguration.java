package livelessons.xauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
class XAuthConfiguration {

	@Bean
	XAuthTokenRestController xAuthTokenRestController(AuthenticationManager am,
			UserDetailsService uds) {
		return new XAuthTokenRestController(am, uds);
	}

	@Bean
	XAuthTokenFilter tokenFilter(UserDetailsService uds) {
		return new XAuthTokenFilter(uds);
	}

}
