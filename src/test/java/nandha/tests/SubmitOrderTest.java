package nandha.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nandha.TestComponents.BaseTest;
import nandha.pageobjects.CartPage;
import nandha.pageobjects.CheckOutPage;
import nandha.pageobjects.ConfirmationPage;
import nandha.pageobjects.LandingPage;
import nandha.pageobjects.OrderPage;
import nandha.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
		@Test(dataProvider="getData",groups="Purchase")
		public void submitOrder(HashMap<String,String> input) throws IOException {
		
		String countryName = "India";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
	
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.checkOut();
		
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		
		String confirmMessage = confirmationPage.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
//	To verify product is displayed in order history

		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest() {
			
			ProductCatalogue productCatalogue = landingPage.loginApplication("nandha@gmail.com", "N&a@1999");
			OrderPage orderPage = productCatalogue.goToOrdersPage(); 
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
			
		}	
		
//		public String getScreenShot(String testCaseName) throws IOException {
//			
//			TakesScreenshot ts =(TakesScreenshot)driver;
//			File source = ts.getScreenshotAs(OutputType.FILE);
//			File filePAth = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
//			FileUtils.copyFile(source, filePAth);
//			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
//		}
//		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
			
			List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\nandha\\data\\PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};//src\test\java\nandha\data\PurchaseOrder.json
//			\\src\\test\\java\\nandha\\data\\PurchaseOrder.json
			
//			data.get(0)--> will return first data in the HashMap
		}
		}
//		@DataProvider
//		public Object[][] getData() {
//		return new Object[][] {{"nandha@gmail.com", "N&a@1999","ZARA COAT 3"},{"nandha1@gmail.com", "N&a@1999","ADIDAS ORIGINAL"}};
//		//Object is a generic data type & it handles integer,String,float,double
//					
//				}

		
//		HashMap<String,String> map =new HashMap<String,String>();//since loginApplication() method expects String, String Object is changed to String
//		map.put("email", "nandha@gmail.com");
//		map.put("password", "N&a@1999");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 =new HashMap<String,String>();
//		map1.put("email", "nandha1@gmail.com");
//		map1.put("password", "N&a@1999");
//		map1.put("product", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};//Object is a generic data type & it handles integer,String,float,double
		

