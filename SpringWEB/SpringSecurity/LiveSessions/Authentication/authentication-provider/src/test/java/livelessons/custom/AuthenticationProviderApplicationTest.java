package livelessons.custom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest(classes = { AuthenticationProviderApplication.class,
		AuthenticationProviderApplicationTest.Replacer.class })
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthenticationProviderApplicationTest {

	private static final String USER = "USER", PW = "PW";

	@Configuration
	public static class Replacer {

		@Bean
		BeanPostProcessor beanPostProcessor() {
			return new BeanPostProcessor() {

				@Override
				public Object postProcessBeforeInitialization(Object bean,
						String beanName) throws BeansException {
					if (bean instanceof AtlassianCrowdAuthenticationProvider) {
						// want to replace it with one that we control for the test.
						return new AtlassianCrowdAuthenticationProvider(USER, PW);
					}
					else {
						return bean;
					}
				}

				@Override
				public Object postProcessAfterInitialization(Object bean, String beanName)
						throws BeansException {
					return bean;
				}
			};
		}

	}

	@Autowired
	private MockMvc mockMvc;

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	public void config(AtlassianCrowdAuthenticationProvider ap) throws Exception {
		this.log.info(String.format(
				"attempting to authenticate using username '%s' and password '%s'",
				ap.hardcodedUsername, ap.hardcodedPassword));
	}

	@Test
	public void login() throws Exception {

		String name = USER, pw = PW;
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/greet").header(
						HttpHeaders.AUTHORIZATION, basicAuthorizationHeader(name, pw)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(result -> {
					String body = result.getResponse().getContentAsString();
					Assert.assertEquals(body, "hello, " + name + "!");
				});
	}

	private String basicAuthorizationHeader(String u, String p) {
		String auth = u + ':' + p;
		byte[] encoded = Base64.getEncoder()
				.encode(auth.getBytes(StandardCharsets.ISO_8859_1));
		return "Basic " + new String(encoded);
	}

}