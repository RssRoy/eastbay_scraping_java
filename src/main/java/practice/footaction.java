package practice;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

public class footaction {
	public static void mouseClickByLocator(WebDriver driver, String locator) {
		WebElement el = driver.findElement(By.cssSelector(locator));
		Actions builder = new Actions(driver);
		builder.moveToElement(el).click(el);
		builder.perform();
	}

	public static void main(String[] args) throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver-v0.19.1-linux64/geckodriver");
		WebDriver driver = new FirefoxDriver(options);

		driver.get("https://www.footaction.com/_-_/keyword-air+jordan+shoes?Ns=P_NewArrivalDateEpoch%7C1&cm_SORT=New%20Arrivals");
		Actions action = new Actions(driver);
		//action.moveToElement((WebElement) driver.findElements(By.cssSelector("#endeca_search_results > ul > li:nth-child(1) > span > a:nth-child(2) > img"))).click((WebElement) driver.findElements(By.cssSelector("#endeca_search_results > ul > li:nth-child(1) > span > a:nth-child(2)"))).build().perform();
		Thread.sleep(10000);
		try {
			  driver.findElement(By.cssSelector("#overlay_header_close_button")).click(); }
			  catch(Exception ex) { ex.printStackTrace(); }
		List<WebElement> allLinks = driver.findElements(By.cssSelector("#endeca_search_results > ul"));
		Iterator<WebElement> itr = allLinks.iterator();
		String parent = driver.getWindowHandle();
		while (itr.hasNext()) {
			//driver.findElement(By.cssSelector(
				//	"li > span > a:nth-child(2)")).click();
			WebElement element = driver.findElement(By.cssSelector(
					"li > span > a:nth-child(2)"));
			System.out.println("anchor tag found");
			String ahref=element.getAttribute("href");
			System.out.println(ahref);
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
			driver.findElement(By.cssSelector("li > span > a:nth-child(2) > img")).sendKeys(selectLinkOpeninNewTab);
			Thread.sleep(5000);
			try {
				  driver.findElement(By.cssSelector("#overlay_header_close_button")).click(); }
				  catch(Exception ex) { ex.printStackTrace(); }
			
			Thread.sleep(5000);
		}
		driver.close();

	}

}
