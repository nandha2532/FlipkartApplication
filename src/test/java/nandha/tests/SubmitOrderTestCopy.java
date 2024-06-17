package nandha.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import nandha.pageobjects.CartPage;
import nandha.pageobjects.CheckOutPage;
import nandha.pageobjects.ConfirmationPage;
import nandha.pageobjects.LandingPage;
import nandha.pageobjects.ProductCatalogue;

public class SubmitOrderTestCopy {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		String countryName = "India";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		LandingPage landingPage = new LandingPage(driver); // send driver as argument to the LandingPage()
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("nandha@gmail.com", "N&a@1999");
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
	
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.checkOut();
		
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		
		String confirmMessage = confirmationPage.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();	
	}

}
