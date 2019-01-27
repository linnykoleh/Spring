package livelessons.custom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
public class AuthenticationProviderApplication {

	public static void main(String args[]) {
		SpringApplication.run(AuthenticationProviderApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	public static class BasicAuthorizationConfig extends WebSecurityConfigurerAdapter {

		private final AtlassianCrowdAuthenticationProvider authenticationProvider;

		public BasicAuthorizationConfig(
				AtlassianCrowdAuthenticationProvider authenticationProvider) {
			this.authenticationProvider = authenticationProvider;
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(this.authenticationProvider);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().and().httpBasic().and().authorizeRequests().anyRequest()
					.authenticated();
		}

	}

}

@RestController
class GreetingsRestController {

	@GetMapping("/greet")
	String greet(Principal p) {
		return "hello, " + p.getName() + "!";
	}

}

@Component
class AtlassianCrowdAuthenticationProvider implements AuthenticationProvider {

	private final Log log = LogFactory.getLog(getClass());

	// visible for testing.
	final String hardcodedUsername, hardcodedPassword;

	AtlassianCrowdAuthenticationProvider(@Value("${username:user}") String usr,
			@Value("${password:pw}") String pw) {
		this.hardcodedUsername = usr;
		this.hardcodedPassword = pw;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (isValid(username, password)) {
			return new UsernamePasswordAuthenticationToken(username, password,
					AuthorityUtils.createAuthorityList("USER"));
		}

		throw new BadCredentialsException(
				"couldn't authenticate (" + authentication + ")");
	}

	private boolean isValid(String username, String password) {
		return (username.equalsIgnoreCase(this.hardcodedUsername)
				&& password.equalsIgnoreCase(this.hardcodedPassword));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
