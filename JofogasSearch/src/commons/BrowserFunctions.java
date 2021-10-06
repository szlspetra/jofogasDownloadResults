package commons;

import org.openqa.selenium.WebDriver;

public class BrowserFunctions {
	WebDriver driver;
	
	public BrowserFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void goToPage (String page) {
		driver.navigate().to(page);
	}
}
