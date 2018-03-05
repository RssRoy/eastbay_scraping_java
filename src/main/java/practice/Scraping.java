package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Scraping {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("user.dir"));
		String pwd = System.getProperty("user.dir");

		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("permissions.default.image", 2);
		// System.setProperty("webdriver.chrome.driver",
		// "src/main/resources/chromedriver");
		// System.setProperty("webdriver.chrome.driver",
		// "src/main/resources/chromedriver_win32/chromedriver.exe");
		// System.setProperty("webdriver.firefox.driver", pwd+"/geckodriver");
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver-v0.19.1-linux64/geckodriver");
		// ChromeOptions option =new ChromeOptions();
		// option.addExtensions(new File(pwd+"/Block-image_v1.0.crx"));
		// WebDriver driver = new ChromeDriver(option);
		WebDriver driver = new FirefoxDriver(options);
		int page_no = 1;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?cm_PAGE=180&Rpp=180&Ns=P_NewArrivalDateEpoch%7C1");
		// String href= driver.findElement(By.cssSelector("#endeca_search_results > ul >
		// li:nth-child(1) > a")).getAttribute("href");
		// System.out.println(href);
		Map<String, String> multiHref = new HashMap<String, String>();
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, "t");
		// driver.findElement(By.cssSelector("#endeca_search_results > ul >
		// li:nth-child(1) > a:nth-child(1) > span.product_image >
		// img")).sendKeys(selectLinkOpeninNewTab);
		// System.exit(0);
		int i = 1;
		for (i = 1; i < 226; i++) {
			try {

				// check for li
				String liClassValue = driver
						.findElement(By.cssSelector("#endeca_search_results > ul > li:nth-child(" + i + ")"))
						.getAttribute("class");
				if (liClassValue.equals("clearRow")) {
					continue;
				} else {

					try {
						String href = driver
								.findElement(
										By.cssSelector("#endeca_search_results > ul > li:nth-child(" + i + ") > a"))
								.getAttribute("href");
						String img_url = driver.findElement(By.cssSelector("#endeca_search_results > ul > li:nth-child("
								+ i + ") > a:nth-child(1) > span.product_image > img")).getAttribute("src");
						// String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
						// driver.findElement(By.linkText(href)).sendKeys(selectLinkOpeninNewTab);
						System.out.println("New href--- " + href + "  for li " + i);
						multiHref.put(href, img_url);// .add(img_url,href);
					} catch (NoSuchElementException ex) {
						System.out.println("Khatam ho gaya link bhai");
					}
				}

			} catch (Exception ex) {

				ex.printStackTrace();
				break;
			}

		}
		driver.findElement(
				By.cssSelector("#endecaResultsWrapper > div:nth-child(3) > div > div.endeca_pagination > a.next"))
				.click();
		List<WebElement> shoelis = driver.findElements(By.cssSelector("#endeca_search_results > ul > li"));
		int lisize = shoelis.size();
		for (i = 1; i <= lisize; i++) {
			try {

				// check for li
				String liClassValue = driver
						.findElement(By.cssSelector("#endeca_search_results > ul > li:nth-child(" + i + ")"))
						.getAttribute("class");
				if (liClassValue.equals("clearRow")) {
					continue;
				} else {

					try {
						String href = driver
								.findElement(
										By.cssSelector("#endeca_search_results > ul > li:nth-child(" + i + ") > a"))
								.getAttribute("href");
						String img_url = driver.findElement(By.cssSelector("#endeca_search_results > ul > li:nth-child("
								+ i + ") > a:nth-child(1) > span.product_image > img")).getAttribute("src");
						System.out.println("New href--- " + href + "  for li " + i);
						multiHref.put(href, img_url);// .add(img_url,href);
					} catch (NoSuchElementException ex) {
						System.out.println("Something went wrong");
					}
				}

			} catch (Exception ex) {

				ex.printStackTrace();
				break;
			}
		}

		// System.out.println("All hrefs all together: ---" + multiHref.toString());
		System.out.println("Total no of shoes : " + multiHref.size());
		// int hsize=multiHref.size();
		// Iterator itr = multiHref.iterator();
		// WebDriver driver1 = new ChromeDriver(option);
		for (Map.Entry m : multiHref.entrySet()) {
			// driver1.get((String) itr.next());
			// Thread.sleep(500);
			System.out.println("shoe url---" + m.getKey());
			String shoeurl = m.getKey().toString();
			String shoeimg = m.getValue().toString();
			System.out.println("image url--" + shoeimg);
			driver.get(shoeurl);
			String title = driver.findElement(By.cssSelector("#product_title")).getText();
			System.out.println("title---" + title);
			String price = null;
			try {
				price = driver.findElement(By.cssSelector("#sale_price")).getText();
			} catch (Exception ex) {
				try {
					price = driver.findElement(By.cssSelector("#list_price")).getText();
				} catch (Exception ex1) {
					System.out.println("No price found");
					ex1.printStackTrace();
				}
			}

			System.out.println("price--" + price.substring(price.indexOf("$"), price.length()));
			List<String> shoesize = new ArrayList<String>();
			driver.findElement(By.cssSelector("#current_size_display")).click();
			List<WebElement> sizes = driver.findElements(By.cssSelector("#size_selection_list > a.grid_size"));
			int s = sizes.size();
			System.out.println("s" + s);
			for (int j = 1; j <= s; j++) {
				String sz = null;
				try {
					sz = driver.findElement(By.cssSelector("a.in-stock:nth-child(" + j + ")")).getText();
					shoesize.add(sz);
				} catch (Exception ex) {
					continue;
				}

			}
			System.out.println("Shoe sizes in stock are : " + shoesize.toString());
			// Thread.sleep(500);

		}
		driver.close();

	}
}