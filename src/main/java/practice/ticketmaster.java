package practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ticketmaster {
	
public static void main(String[] args) throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("/home/ranjit/proj/santanu-poc/Google-Translate.crx"));
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		
			
		
		options.addArguments("--lang=en");//.addArguments(“–lang= sl”);
		  WebDriver driver = new ChromeDriver(options);
		  driver.get("chrome-extension://aapbdbdomjkkjkaonfhkkikfgjllcleb/options.html");
		  driver.findElement(By.xpath("//*[@id=\"popup-option-content\"]/div[5]/span")).click();
		  driver.findElement(By.xpath("//*[@id=\"saveBtn\"]")).click();
		  Thread.sleep(5000);
		  
		  //Point coordinates =new Point(0.5, 0.5);
		  //System.out.println("Co-ordinates"+coordinates); 
		  Robot robot = new Robot();
		  //robot.mouseMove(coordinates.getX(),coordinates.getY()); 
		  robot.mousePress( InputEvent.BUTTON1_DOWN_MASK);
		  robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		  //driver.get("https://medicalpolicy.nebraskablue.com/home");
		  driver.get("https://www.ticketmaster.no/musikk/alle-musikk/10001/events?countries=578");
		  try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  driver.findElement((By.cssSelector("#national-events > div")));
		  System.out.println(driver.findElement((By.cssSelector("#national-events > div"))).getSize());
		  
		  /*int i=1;
		  for (i=1;i<=460;i++) {
			  System.out.println(driver.findElement(By.xpath("//*[@id=\"checking\"]/div/table/tbody/tr["+i+"]/td[1]/a")).getAttribute("href").toString());
		  }*/
		  //driver.
		  driver.close();
		  
	}

}
