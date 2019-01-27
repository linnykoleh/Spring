package livelessons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.security.Principal;

@SpringBootApplication
public class AuthenticationApplication {

    private Log log = LogFactory.getLog(getClass());

    public static void main(String args[]) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Profile("memory")
    UserDetailsManager memory() {
        log.info("starting the memory " + InMemoryUserDetailsManager.class.getName());
        return new InMemoryUserDetailsManager();
    }

    @Bean
    @Profile("jdbc")
    UserDetailsManager jdbc(DataSource ds) {
        log.info("starting the JDBC " + JdbcUserDetailsManager.class.getName());
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager();
        jdbc.setDataSource(ds);
        return jdbc;
    }

    @Configuration
    @EnableWebSecurity
    public static class BasicAuthorizationConfig extends WebSecurityConfigurerAdapter {

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
