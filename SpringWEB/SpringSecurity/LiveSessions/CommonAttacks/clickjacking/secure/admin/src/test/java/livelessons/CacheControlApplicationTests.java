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

import static org.assertj.core.api.Assertions.assertThat;

@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CacheControlApplicationTests {

	@Autowired
	private WebDriver driver;

	@Test
	public void indexViewMessage() {
		IndexPage index = IndexPage.to(this.driver, IndexPage.class);
		index.assertAt();
		assertThat(index.message()).isEqualTo("Hello Security!");
	}

}
