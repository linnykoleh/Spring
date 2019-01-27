package livelessons.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
public class LdapAuthenticationApplication {

	public static void main(String args[]) {
		SpringApplication.run(LdapAuthenticationApplication.class, args);
	}

	/**
	 * @see <a href=
	 * "https://www.quora.com/What-is-LDAP-and-how-does-LDAP-authentication-work">What is
	 * LDAP</a>
	 */
	@Profile("ldap")
	@Configuration
	public static class LdapConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.ldapAuthentication().userDnPatterns("uid={0},ou=people")
					.groupSearchBase("ou=groups").contextSource()
					.url("ldap://localhost:8389/dc=springframework,dc=org").and()
					.passwordCompare().passwordEncoder(new LdapShaPasswordEncoder())
					.passwordAttribute("userPassword");
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
