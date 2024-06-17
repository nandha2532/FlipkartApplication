package nandha.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import nandha.TestComponents.BaseTest;
import nandha.TestComponents.Retry;
import nandha.pageobjects.CartPage;
import nandha.pageobjects.CheckOutPage;
import nandha.pageobjects.ConfirmationPage;
import nandha.pageobjects.LandingPage;
import nandha.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

		@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
		public void submitOrder() throws IOException {
		
		landingPage.loginApplication("nandha@gmail.com", "N&a@1999");
//		Assert.assertEquals("Incorrect email 0r password.", landingPage.getErrorMessaage());
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessaage());
			
		}
		
		@Test
		public void productErrorValidation() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("nandha1@gmail.com", "N&a@1999");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
//		Assert.assertFalse(match);
		Assert.assertTrue(match);
		}

}
