package livelessons.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * curl -v -X POST -F "username=rob" -F"password=pw" http://localhost:8080/authenticate
 * curl -v -H"x-auth-token: rob:1532068547605:c704d3c1c78a17922070a8e7b7e02144"
 * http://localhost:8080/greet
 */
@SpringBootApplication
public class CustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomApplication.class, args);
	}

}

@Configuration
class UserConfiguration {

	@Bean
	InMemoryUserDetailsManager authentication() {
		return new InMemoryUserDetailsManager(user("rob"), user("josh"));
	}

	private static UserDetails user(String u) {
		return User.withDefaultPasswordEncoder().roles("USER").username(u).password("pw")
				.build();
	}

}

@Configuration
@EnableWebSecurity
class CustomSecurity extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().mvcMatchers("/greet").authenticated().anyRequest()
				.permitAll().and().httpBasic().and().csrf().disable();
	}

}

@RestController
class GreetingsRestController {

	@GetMapping("/greet")
	String greet(Principal p) {
		return "hello, " + p.getName() + "!";
	}

}
