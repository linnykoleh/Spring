package livelessons;

import livelessons.webdriver.IndexPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BankApplicationTests {
	@Autowired
	private WebDriver driver;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void transfer() {
		IndexPage index = IndexPage.to(this.driver, IndexPage.class);
		index.assertAt();

		assertThat(index.balance()).isEqualTo(100);

		index = index.transfer(50);

		assertThat(index.balance()).isEqualTo(50);
	}

	@Test
	public void csrfProtectionEnabled() throws Exception {
		this.mockMvc.perform(post("/transfer").param("amount", "0"))
			.andExpect(status().isForbidden());
	}
}
