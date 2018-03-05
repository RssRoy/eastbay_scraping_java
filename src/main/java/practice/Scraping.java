package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Scraping {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("user.dir"));

		List<String> urls = new ArrayList<String>();

		urls.add("https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&cm_SORT=Initial%20Results");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_AverageOverallRating%7C1&cm_SORT=Product%20Rating%20%28High%20to%20Low%29");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_ModelName%7C0&cm_SORT=Alphabetical%20%28A%20to%20Z%29");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_NewArrivalDateEpoch%7C1&cm_SORT=New%20Arrivals");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_StyleSalePrice%7C0&cm_SORT=Price%20%28Low%20to%20High%29");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_StyleSalePrice%7C1&cm_SORT=Price%20%28High%20to%20Low%29");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_TopSalesAmount%7C1&cm_SORT=Top%20Sellers");
		urls.add(
				"https://www.eastbay.com/_-_/keyword-air+jordan+shoes?Rpp=180&Ns=P_BrandName%7C0&cm_SORT=Brand%20Name%20A-Z");

		for (String url : urls) {
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("permissions.default.image", 2);
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver-v0.19.1-linux64/geckodriver");
			WebDriver driver = new FirefoxDriver(options);
			
			driver.get(url);
			Map<String, String> multiHref = new HashMap<String, String>();
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
							String img_url = driver
									.findElement(By.cssSelector("#endeca_search_results > ul > li:nth-child(" + i
											+ ") > a:nth-child(1) > span.product_image > img"))
									.getAttribute("src");
							// String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
							// driver.findElement(By.linkText(href)).sendKeys(selectLinkOpeninNewTab);
							//System.out.println("New href--- " + href + "  for li " + i);
							multiHref.put(href, img_url);// .add(img_url,href);
						} catch (NoSuchElementException ex) {
							System.out.println("Unknown thing found");
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
							String img_url = driver
									.findElement(By.cssSelector("#endeca_search_results > ul > li:nth-child(" + i
											+ ") > a:nth-child(1) > span.product_image > img"))
									.getAttribute("src");
							// System.out.println("New href--- " + href + " for li " + i);
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

			System.out.println("Total no of shoes : " + multiHref.size()+ " for the url : "+url);
			for (Map.Entry m : multiHref.entrySet()) {
				// driver1.get((String) itr.next());
				// Thread.sleep(500);
				System.out.println("shoe url---" + m.getKey());
				String shoeurl = m.getKey().toString();
				String shoeimg = m.getValue().toString();
				System.out.println("image url--" + shoeimg);
				driver.get(shoeurl);
				String imgsrc=null;
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				try{
					imgsrc=driver.findElement(By.cssSelector("#other_styles > a.selected > img")).getAttribute("src");
				}catch(Exception ex11) {
					imgsrc = (String) jse.executeScript("return arguments[0].toDataURL('image/png');", driver.findElement(By.cssSelector("#s7ZoomViewerViewer_zoomView > div > div:nth-child(3) > canvas")));
					System.out.println(imgsrc);
				}
				System.out.println("image link -- "+imgsrc);
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
}