package livelessons.custom;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AtlassianCrowdAuthenticationProviderTest {

	private final String username = "rob";

	private final String password = "b0r";

	private final AtlassianCrowdAuthenticationProvider provider = new AtlassianCrowdAuthenticationProvider(
			this.username, this.password);

	@Test
	public void authenticateSuccess() {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication authenticate = this.provider.authenticate(token);
		Assert.assertTrue("the authentication should be valid",
				authenticate.isAuthenticated());
	}

	@Test(expected = BadCredentialsException.class)
	public void authenticateFail() {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				"WR0NG", "N0P3");
		this.provider.authenticate(token);
		Assert.fail("this should fail");
	}

	@Test
	public void supports() {
		Assert.assertTrue(
				this.provider.supports(UsernamePasswordAuthenticationToken.class));
		Assert.assertTrue(this.provider.supports(JaasAuthenticationToken.class));
		Assert.assertFalse(this.provider.supports(RuntimeException.class));
	}

}