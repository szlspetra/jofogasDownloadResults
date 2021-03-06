package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import commons.*;
import pages.*;

public class main_searchHouse {
	
	SetupChromeDriver setChroDri;
	WebDriver driver;
	BrowserFunctions broFun;
	ListView listView;
	ElementFunctions eleFun;
	List<String> strAdURL;
	String strURLBase = "https://ingatlan.jofogas.hu/";
	String strCities = "pest/biatorbagy+budakalasz+budaors+diosd+herceghalom+pilisborosjeno+pilisszentivan+pilisvorosvar+pomaz+solymar+tarnok+torokbalint+urom/";
	String strCities_Bp = "budapest/i-kerulet+ii-kerulet+iii-kerulet+iv-kerulet+xi-kerulet+xii-kerulet+xiii-kerulet+xiv-kerulet+xxii-kerulet/";
	String strType_House = "haz?";
	String strType_semiDHouse = "sorhaz-ikerhaz-hazresz?";
	String strMaxPrice = "max_price=65000000&min_price=9000000";	
	
	String arrData[];
	
	@Test
	public void setPage() {

		setChroDri = new SetupChromeDriver();
		driver = setChroDri.getDriver();
		broFun = new BrowserFunctions(driver);
		eleFun = new ElementFunctions(driver);
		//Go to page 
		broFun.goToPage(strURLBase+strCities+strType_House+strMaxPrice);
		//Accept Cookies
		listView = new ListView(driver);
		listView.clickToCookieButton();
		System.out.println("1: setPage OK");
		
		saveURLs();
		setBpAds();
		setBpSemiHouseAds();
		setPestSemiHouseAds();
		openAds();
		closeBrowser();
	}
	
	public void saveURLs() {
		strAdURL = new ArrayList<String>();
		while(listView.isNextPageButtonExists()) {
			strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
			listView.clickNextPage();
		}
		System.out.println("2: saveURLs OK");
	}
	
	public void setBpAds() {
		broFun.goToPage(strURLBase+strCities_Bp+strType_House+strMaxPrice);
		while(listView.isNextPageButtonExists()) {
			strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
			listView.clickNextPage();
		}
		System.out.println("3: setBpAds OK");
	}
	
	public void setBpSemiHouseAds() {
		broFun.goToPage(strURLBase+strCities_Bp+strType_semiDHouse+strMaxPrice);
		while(listView.isNextPageButtonExists()) {
			strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
			listView.clickNextPage();
		}
		System.out.println("4: setBpSemiHouseAds OK");
	}
	
	public void setPestSemiHouseAds() {
		broFun.goToPage(strURLBase+strCities+strType_semiDHouse+strMaxPrice);
		while(listView.isNextPageButtonExists()) {
			strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
			listView.clickNextPage();
		}
		System.out.println("5: setPestSemiHouseAds OK");
	}
	
	public void openAds() {
		DetailedPage dp = new DetailedPage(driver);
		arrData = new String[]{"Price","City","Garden size","House size","Building type","Category","Condition","Heating","Proprietary","Description","URL"};
		Excel e = new Excel();
		String strPath = "JofogasSearch\\output\\house_pest.xlsx";
		
		//Set header in Excel
		try {
			e.writeExcel(strPath, "brick", arrData);
			e.writeExcel(strPath, "other", arrData);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for (int i=0; i<strAdURL.size();i++) {
			//Go to ads URL
			System.out.println(strAdURL.get(i));
			broFun.goToPage(strAdURL.get(i));
			arrData = new String[]{dp.getPrice(),dp.getCity(),dp.getGarden(),dp.getSize(),dp.getBuilding(),dp.getCategory(),dp.getCondition(),dp.getHeating(),dp.getProprietary(),dp.getDescription(),strAdURL.get(i)};	
			if(dp.getBuilding().equals("t?gla")) {
				try {
					e.writeExcel(strPath, "brick", arrData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					e.writeExcel(strPath, "other", arrData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		System.out.println("6: openAds OK");
	}
	
	public void closeBrowser() {
		driver.close();
	}

}