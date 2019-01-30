package livelessons;

import livelessons.webdriver.LogoutPage;
import livelessons.webdriver.IndexPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import livelessons.webdriver.LoginPage;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HelloSecurityTests {

	@Autowired
	private WebDriver driver;

	@Autowired
	private SecurityProperties securityProperties;

	@Test
	public void indexRequiresLogin() {
		LoginPage login = IndexPage.to(this.driver, LoginPage.class);
		login.assertAt();
	}

	@Test
	public void loginFailure() {
		LoginPage login = IndexPage.to(this.driver, LoginPage.class);
		login.form().username("user").password("invalid").login(LoginPage.class)
				.assertAt();
	}

	@Test
	public void loginSuccess() {
		LoginPage login = IndexPage.to(this.driver, LoginPage.class);
		login.form().username(this.securityProperties.getUser().getName())
				.password(this.securityProperties.getUser().getPassword())
				.login(IndexPage.class).assertAt();
	}

	@Test
	public void logout() {
		LoginPage login = IndexPage.to(this.driver, LoginPage.class);

		login.form().username(this.securityProperties.getUser().getName())
				.password(this.securityProperties.getUser().getPassword())
				.login(IndexPage.class).assertAt();

		LogoutPage.to(this.driver).assertAt().logout().assertAt();

		login = IndexPage.to(this.driver, LoginPage.class);
		login.assertAt();
	}

}
