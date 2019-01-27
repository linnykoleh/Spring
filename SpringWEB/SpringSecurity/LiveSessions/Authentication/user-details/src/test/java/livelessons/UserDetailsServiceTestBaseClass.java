package livelessons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserDetailsServiceTestBaseClass {

	private final static String USERNAME = "user1";

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(USERNAME)
	public void authenticate() throws Exception {

		String expectedString = String.format("hello, %s!", USERNAME);

		this.mvc.perform(get("/greet")).andExpect(status().isOk())
				.andExpect(content().string(expectedString));
	}

	@Test
	public void loadUserByUsername() {
		UserDetails user = this.userDetailsManager.loadUserByUsername("user1");
		Assert.assertNotNull(user);
	}

	@Test
	public void createUser() {
		String user = "user";

		this.userDetailsManager.deleteUser(user);

		UserDetails jane = User.withDefaultPasswordEncoder().username(user).password("pw")
				.roles("USER").build();
		this.userDetailsManager.createUser(jane);
		Assert.assertTrue(this.userDetailsManager.userExists(jane.getUsername()));
	}

}

@Configuration
@Profile("!ldap")
class Initializer implements ApplicationListener<ApplicationReadyEvent> {

	private final PasswordEncoder passwordEncoder;

	private final UserDetailsManager userDetailsManager;

	Initializer(PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsManager = userDetailsManager;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent evt) {
		Consumer<UserDetails> process = user -> {
			this.userDetailsManager.deleteUser(user.getUsername());
			this.userDetailsManager.createUser(user);
		};

		Collection<UserDetails> userDetails = IntStream.range(0, 5)
				.mapToObj(i -> new User("user" + i,
						this.passwordEncoder.encode("password" + i), true, true, true,
						true, AuthorityUtils.createAuthorityList("USER")))
				.collect(Collectors.toList());

		userDetails.forEach(process);
	}

}