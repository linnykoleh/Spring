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

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Rob Winch
 */
public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void assertAt() {
		assertThat(this.driver.getTitle()).isEqualTo("Please sign in");
	}

	public Form form() {
		return new Form(this.driver);
	}

	public class Form {

		@FindBy(name = "username")
		private WebElement username;

		@FindBy(name = "password")
		private WebElement password;

		@FindBy(css = "button")
		private WebElement button;

		public Form(SearchContext context) {
			PageFactory.initElements(new DefaultElementLocatorFactory(context), this);
		}

		public Form username(String username) {
			this.username.sendKeys(username);
			return this;
		}

		public Form password(String password) {
			this.password.sendKeys(password);
			return this;
		}

		public <T> T login(Class<T> page) {
			this.button.click();
			return PageFactory.initElements(LoginPage.this.driver, page);
		}

	}

}
