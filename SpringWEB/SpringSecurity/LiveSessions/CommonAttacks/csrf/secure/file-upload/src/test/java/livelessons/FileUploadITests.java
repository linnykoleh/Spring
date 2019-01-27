package livelessons;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import livelessons.webdriver.IndexPage;
import livelessons.webdriver.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class FileUploadITests {
	@LocalServerPort
	private int serverPort;
	@Autowired
	private SecurityProperties securityProperties;

	@Value("classpath:/livelessons/the-file.txt")
	private Resource theFile;

	private WebDriver driver;

	@Before
	public void setup() {
		this.driver = new HtmlUnitDriver(BrowserVersion.CHROME);
	}

	@After
	public void cleanup() {
		this.driver.close();
	}

	@Test
	public void upload() throws Exception {
		LoginPage login = IndexPage.to(this.driver, this.serverPort, LoginPage.class);
		IndexPage index = login.form()
				.username(this.securityProperties.getUser().getName())
				.password(this.securityProperties.getUser().getPassword())
				.login(IndexPage.class);
		index.assertAt();

		index
			.upload(this.theFile.getFile().getAbsolutePath())
			.assertAt();
	}
}
