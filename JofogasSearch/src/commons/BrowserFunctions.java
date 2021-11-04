package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class BrowserFunctions {
	WebDriver driver;
	
	public BrowserFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void goToPage (String page) {
		driver.navigate().to(page);
	}
	
	public void openNewTab (String link) {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		driver.findElement(By.xpath(link)).sendKeys(selectLinkOpeninNewTab);
	}
	
}
