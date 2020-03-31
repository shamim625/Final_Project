package coding;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class AutomationPractice {
	static WebDriver driver;
	static WebDriverWait wait;
	static JavascriptExecutor js;
	static Actions action;

	public static void main(String[] args) throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver= new ChromeDriver();
		js= (JavascriptExecutor) driver;
		action= new Actions(driver);
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		
		WebElement signin = driver.findElement(By.xpath("//*[contains(text(),'Sign in')]"));
		signin.click();
		WebElement email= driver.findElement(By.xpath("//*[@id='email']"));
		email.sendKeys("finalproject@gmail.com");
		WebElement password= driver.findElement(By.xpath("//*[@id='passwd']"));
		password.sendKeys("finalproject");
		WebElement login= driver.findElement(By.xpath("//*[@id='SubmitLogin']"));
		login.click();
		
		String title= driver.getTitle();
		System.out.println("This is the actual titl eafter login: "+title);
		
		String expectedtitle= "My account - My Store";
		System.out.println("This is the expected title: "+expectedtitle);
		
		Assert.assertEquals(expectedtitle, title);
		if (title.equals(expectedtitle)) {
			System.out.println("Verified title: "+ title);
		} else {
			System.out.println("Actual title doesn't match with expected title");
		}
		
		Thread.sleep(2000);
		WebElement dresses= driver.findElement(By.xpath("(//*[@title='Dresses'])[2]"));
		dresses.click();
		
		WebElement p1= driver.findElement(By.xpath("(//*[@itemprop='price'])[2]"));
		WebElement p2= driver.findElement(By.xpath("(//*[@itemprop='price'])[4]"));
		WebElement p3= driver.findElement(By.xpath("(//*[@itemprop='price'])[6]"));
		WebElement p4= driver.findElement(By.xpath("(//*[@itemprop='price'])[8]"));
		WebElement p5= driver.findElement(By.xpath("(//*[@itemprop='price'])[10]"));
		
		
		ArrayList<String> p= new ArrayList<String>();
		p.add(p1.getText());
		p.add(p2.getText());
		p.add(p3.getText());
		p.add(p4.getText());
		p.add(p5.getText());
		
		for(String prices: p) {
			
			System.out.println("Price values: "+ prices);
		}
		
		Collections.sort(p, Collections.reverseOrder());
		for (String prices: p) {
			System.out.println("Price Values in descending order: "+prices);
		}
		
		
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,900)");
		
		
		
		WebElement rightblock= driver.findElement(By.xpath("(//*[@class='right-block'])[2]"));
		WebElement addtocart = driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[2]"));
		WebElement proceedtocheckout= driver.findElement(By.xpath("(//*[@class='icon-chevron-right right'])[2]"));
		
		Thread.sleep(2000);
		action.moveToElement(rightblock).perform();
		
		addtocart.click();
		Thread.sleep(2000);
		proceedtocheckout.click();
		
		js.executeScript("window.scrollBy(0, 450)");
		
		WebElement product= driver.findElement(By.xpath("//*[@id='total_product']"));
		WebElement shipping= driver.findElement(By.xpath("//*[@id='total_shipping']"));
		WebElement total = driver.findElement(By.xpath("//*[@id='total_price']"));
		
		String productprice= product.getText();
		String shippingprice= shipping.getText();
		String totalprice= total.getText();
		
		System.out.println("Price of the product(s): "+productprice);
		System.out.println("Shipping cost: "+shippingprice);
		System.out.println("Total price with shipping: "+totalprice);
		
		String totalexpectedprice = "$52.99";
		System.out.println("Total expected price with shipping: "+totalexpectedprice);
		
//		Assert.assertEquals(totalexpectedprice, totalprice);
		if(totalprice.equals(totalexpectedprice)) {
			System.out.println("Verified: Total price with shipping matches expected price, which is  " + totalprice);
			
		}
		else {
			System.out.println("Verifed: total price with shipping doesn't match expected price");
		}
		
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -450)");
		
		WebElement logout= driver.findElement(By.xpath("//*[@title='Log me out']"));
		Thread.sleep(1500);
		logout.click();
		
		
		Thread.sleep(2000);
		driver.close();

	}

}
