package livelessons;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.util.FieldUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleUserDetailsServiceTest {

	private final PasswordEncoder passwordEncoder = PasswordEncoderFactories
			.createDelegatingPasswordEncoder();

	private final UserDetailsService userDetailsService = new InMemoryUserDetailsManager(
			this.contributeUsers());

	private final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider() {
		{
			setPasswordEncoder(passwordEncoder);
			setUserDetailsService(userDetailsService);
		}
	};

	@Test
	@SuppressWarnings("unchecked")
	public void encode() throws Exception {
		String encodedPassword = this.passwordEncoder.encode("password");
		Assert.assertTrue(this.passwordEncoder.matches("password", encodedPassword));

		// is the ID in the encoded PW?
		Field idForEncode = fieldFor(DelegatingPasswordEncoder.class, "idForEncode");
		String id = String.class.cast(idForEncode.get(this.passwordEncoder));
		Assert.assertTrue(encodedPassword.contains(id));

		// is the default PasswordEncoder BCrypt?
		Field pwEncoderMapField = fieldFor(DelegatingPasswordEncoder.class,
				"idToPasswordEncoder");
		Map<String, PasswordEncoder> pwEncoderMap = (Map<String, PasswordEncoder>) pwEncoderMapField
				.get(this.passwordEncoder);
		Assert.assertTrue(pwEncoderMap.get(id) instanceof BCryptPasswordEncoder);
	}

	@Test
	public void loadUserByUsername() {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername("user1");
		Assert.assertNotNull(userDetails);
	}

	@Test
	public void authenticate() {
		Authentication authentication = new UsernamePasswordAuthenticationToken("user1",
				"password1");
		Authentication result = this.daoAuthenticationProvider
				.authenticate(authentication);
		Assert.assertTrue(result.isAuthenticated());
	}

	private Collection<UserDetails> contributeUsers() {
		return IntStream.range(0, 5)
				.mapToObj(i -> new User("user" + i,
						this.passwordEncoder.encode("password" + i), true, true, true,
						true, AuthorityUtils.createAuthorityList("USER")))
				.collect(Collectors.toList());
	}

	private Field fieldFor(Class<?> aClass, String fieldName) {
		Field idForEncode = FieldUtils.getField(aClass, fieldName);
		idForEncode.setAccessible(true);
		return idForEncode;
	}

}
