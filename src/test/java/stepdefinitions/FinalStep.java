package stepdefinitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.Timeout;
import junit.framework.Assert;
import pagefactories.Finalpf;

public class FinalStep {
	static WebDriver driver;
	static JavascriptExecutor js;
	static Actions action;
	static Finalpf pf;
	static WebDriverWait wait;

	@Given("User go to the website")
	public void user_go_to_the_website() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		pf = PageFactory.initElements(driver, Finalpf.class);
		wait= new WebDriverWait(driver, 20);

	}

	@When("User click on the log in button")
	public void user_click_on_the_log_in_button() {
		pf.getLoginbutton().click();
	}

	@When("User enter valid email address and password")
	public void user_enter_valid_email_address_and_password() {
		pf.getEmail().sendKeys("finalproject@gmail.com");
		pf.getPassword().sendKeys("finalproject");
	}

	@When("User click on the submit button")
	public void user_click_on_the_submit_button() {
		pf.getSigninbutton().click();
	}

	@When("User verify the  website title")
	public void user_verify_the_website_title() {
		wait.until(ExpectedConditions.titleIs(driver.getTitle()));
		String title = driver.getTitle();
		System.out.println("This is the actual titleafter login: " + title);

		String expectedtitle = "My account - My Store";
		System.out.println("This is the expected title: " + expectedtitle);
		Assert.assertEquals(title, expectedtitle);
		if (title.equals(expectedtitle)) {
			System.out.println("Verified title: " + title);
		} else {
			System.out.println("Actual title doesn't match with expected title");
		}
	}

	@Then("User click on the dress button")
	public void user_click_on_the_dress_button() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(pf.getDressbutton()));
		pf.getDressbutton().click();
	}

	@Then("User print all the prices values in decending order")
	public void user_print_all_the_prices_values_in_decending_order() throws Throwable {
		js.executeScript("window.scrollBy(0,900)");
		
		ArrayList<String> pricelist = new ArrayList<String>();
		wait.until(ExpectedConditions.visibilityOf(pf.getP1()));
		pricelist.add(pf.getP1().getText());
		pricelist.add(pf.getP2().getText());
		pricelist.add(pf.getP3().getText());
		pricelist.add(pf.getP4().getText());
		pricelist.add(pf.getP5().getText());

		for (String prices : pricelist) {

			System.out.println("Price values: " + (prices));
		}
		
		
		Collections.sort(pricelist, Collections.reverseOrder());
		for (String prices : pricelist) {
			System.out.println("Price Values in descending order: " + prices);
		}
	}

	@Then("User select the second dress on that list")
	public void user_select_the_second_dress_on_that_list() throws Throwable {
		wait.until(ExpectedConditions.visibilityOf((pf.getRightblock())));
		action.moveToElement(pf.getRightblock()).perform();
		wait.until(ExpectedConditions.elementToBeClickable(pf.getAddtocart()));
		pf.getAddtocart().click();
		wait.until(ExpectedConditions.elementToBeClickable(pf.getProceedtocheckout()));
		pf.getProceedtocheckout().click();
	}

	@Then("User verify the total price with shipping")
	public void user_verify_the_total_price_with_shipping() throws Throwable {
		js.executeScript("window.scrollBy(0, 450)");
		wait.until(ExpectedConditions.visibilityOf(pf.getProduct()));
		String productprice = pf.getProduct().getText();
		wait.until(ExpectedConditions.visibilityOf(pf.getShipping()));
		String shippingprice = pf.getShipping().getText();
		wait.until(ExpectedConditions.visibilityOf(pf.getTotal()));
		String totalprice = pf.getTotal().getText();

		System.out.println("Price of the product(s): " + productprice);
		System.out.println("Shipping cost: " + shippingprice);
		System.out.println("Total price with shipping: " + totalprice);

		String totalexpectedprice = "$52.99";
		System.out.println("Total expected price with shipping: " + totalexpectedprice);
		Assert.assertEquals("Price matched", totalexpectedprice, totalprice);
		if (totalprice.equals(totalexpectedprice)) {
			System.out.println("Verified: Total price with shipping matches expected price, which is  " + totalprice);

		} else {
			System.out.println("Verifed: total price with shipping doesn't match expected price");
		}
	}

	@Then("User logout and close the browser")
	public void User_logout_and_close_the_browser() throws Throwable {
		js.executeScript("window.scrollTo(0, 0)");
		wait.until(ExpectedConditions.elementToBeClickable(pf.getLogout()));
		pf.getLogout().click();
		driver.close();
	}

}
