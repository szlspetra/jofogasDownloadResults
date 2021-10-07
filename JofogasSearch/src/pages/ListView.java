package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListView {
	WebDriver driver;
	String strCookieButton = "//*[@id=\"CybotCookiebotDialogBodyButtonAccept\"]";
	String strListElements = "//*[@class=\"list-items\"]//*[@class=\"item-title\"]";
	
	public ListView(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToCookieButton () {
		try {
			driver.findElement(By.xpath(strCookieButton)).click();
			
		} catch (Exception e) {
			return;
		}
	}
	
	public void clickToListElement () {
		try {
			driver.findElement(By.xpath(strCookieButton)).click();
			
		} catch (Exception e) {
			return;
		}
	}
	
	
}
