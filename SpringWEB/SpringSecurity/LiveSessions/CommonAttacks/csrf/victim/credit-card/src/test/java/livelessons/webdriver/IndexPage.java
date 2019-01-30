package livelessons.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexPage {

	private WebDriver driver;

	@FindBy(css = "#balance")
	private WebElement balance;

	private WebElement amount;

	@FindBy(css = "input[type=submit]")
	private WebElement submit;

	public IndexPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public static <T> T to(WebDriver driver, Class<T> page) {
		driver.get("http://localhost:8080/");
		return (T) PageFactory.initElements(driver, page);
	}

	public IndexPage assertAt() {
		assertThat(this.driver.getTitle()).isEqualTo("Bank");
		return this;
	}

	public double balance() {
		return Double.parseDouble(this.balance.getText());
	}

	public IndexPage transfer(double amount) {
		this.amount.sendKeys(String.valueOf(amount));
		this.submit.click();
		return PageFactory.initElements(this.driver, IndexPage.class);
	}
}
