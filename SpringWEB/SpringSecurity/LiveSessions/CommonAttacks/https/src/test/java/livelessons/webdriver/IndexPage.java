package livelessons.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexPage {

	private WebDriver driver;

	@FindBy(tagName = "h1")
	private WebElement message;

	public IndexPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public static <T> T to(WebDriver driver, Class<T> page) {
		driver.get("http://localhost:8080/");
		return (T) PageFactory.initElements(driver, page);
	}

	public IndexPage assertAt() {
		assertThat(this.driver.getTitle()).isEqualTo("Hello Security!");
		return this;
	}

	public String message() {
		return this.message.getText();
	}

}
