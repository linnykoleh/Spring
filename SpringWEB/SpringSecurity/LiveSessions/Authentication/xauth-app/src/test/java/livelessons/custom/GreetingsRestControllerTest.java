package livelessons.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingsRestControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Test
	public void greetFail() throws Exception {

		this.mvc.perform(get("/greet").with(anonymous()))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void greetSuccess() throws Exception {

		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
		};

		AtomicReference<String> tokenReference = new AtomicReference<>();

		String rob = "rob";
		this.mvc.perform(
				post("/authenticate").param("username", rob).param("password", "pw"))
				.andExpect(result -> {
					String response = result.getResponse().getContentAsString();
					log.info("response: " + response);

					Map<String, String> map = this.objectMapper.readValue(response,
							typeReference);
					String token = map.get("token");
					Assert.assertTrue(token.contains(rob));
					tokenReference.set(token);
				});

		this.mvc.perform(get("/greet").header("x-auth-token", tokenReference.get()))
				.andExpect(content().string("hello, " + rob + "!"));
	}

}