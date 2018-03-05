package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class webScraping {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

		  WebDriver driver = new ChromeDriver();
		  //driver.get("https://medicalpolicy.nebraskablue.com/home");
		  driver.get("http://www.medicalpolicy.hcsc.net/medicalpolicy/activePolicyPage?lid=j7yn3laf&corpEntCd=TX1");
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  driver.findElement(By.xpath("//*[@id=\"agree_button\"]")).click();
		  driver.findElement(By.xpath("//*[@id=\"center-content\"]/div/div/div[1]/a/div/div")).click();
		  //System.out.println(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/button[2]")).toString());
		  Select dropdown = new Select(driver.findElement(By.id("tablePagination_rowsPerPage")));//.xpath("//*[@id=\"tablePagination_rowsPerPage\"]")));
		 // System.out.println(dropdown.getOptions().size());
		  dropdown.selectByIndex(1);
		  
		  //dropdown.deselectByVisibleText("All");
		  
		  int i=1;
		  for (i=1;i<=460;i++) {
			  System.out.println(driver.findElement(By.xpath("//*[@id=\"checking\"]/div/table/tbody/tr["+i+"]/td[1]/a")).getAttribute("href").toString());
		  }
		  //driver.
		  //driver.close();
		  
	}

}
