/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package livelessons.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Rob Winch
 */
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
