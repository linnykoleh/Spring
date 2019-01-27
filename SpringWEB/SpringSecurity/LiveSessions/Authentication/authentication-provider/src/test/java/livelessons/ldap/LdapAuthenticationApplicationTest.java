package livelessons.ldap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest(classes = { LdapAuthenticationApplication.class })
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("ldap")
public class LdapAuthenticationApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void login() throws Exception {
		String name = "ben", pw = "benspassword";
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