package livelessons.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutPage {

	private WebDriver driver;

	@FindBy(css = "button")
	private WebElement logout;

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public static LogoutPage to(WebDriver driver) {
		driver.get("http://localhost:8080/logout");
		return PageFactory.initElements(driver, LogoutPage.class);
	}

	public LogoutPage assertAt() {
		assertThat(this.driver.getTitle()).isEqualTo("Confirm Log Out?");
		return this;
	}

	public LoginPage logout() {
		this.logout.click();
		return PageFactory.initElements(this.driver, LoginPage.class);
	}

}
