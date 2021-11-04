package commons;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFunctions {
	WebDriver driver;
	
	public ElementFunctions(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Save the attributes value into list
	 * @param xpath			Elements xpath 
	 * @param Attribute		which attributes value want to save
	 * @return				List, which contains the arguments value
	 */
	public List getAttributeValues(String xpath, String attribute) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		List<String> values = new ArrayList<String>();
		
		for (int i=0; i<elements.size();i++){
		      values.add(elements.get(i).getAttribute(attribute));
		}
		return values;
	}


}
