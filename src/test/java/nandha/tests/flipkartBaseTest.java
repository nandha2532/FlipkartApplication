package nandha.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.XL;
import nandha.TestComponents.BaseTest;
import nandha.pages.HomePage;
import nandha.pages.ProductPage;
import nandha.pages.cartPage;

public class flipkartBaseTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {
		HomePage homePage;
		ProductPage productPage;
		cartPage cartPage;

//		launchApplication();
		homePage = new HomePage(driver);
		System.out.println(abs.getTitle());
		productPage = homePage.search(XL.getData("Search", "Product"));

		System.out.println(abs.getTitle());

		productPage.priceMin(XL.getData("Search", "Min"));
		System.out.println(XL.getData("Search", "Min"));
		System.out.println(XL.getData("Search", "Max"));
		productPage.priceMax(XL.getData("Search", "Max"));
		System.out.println("rating is : " + XL.getData("Search", "Rating"));

		productPage.rating(XL.getData("Search", "Rating"));

		productPage.offers(XL.getData("Search", "Offers"));
		productPage.discount(XL.getData("Search", "Discount"));
		productPage.availability();
		productPage.sort(XL.getData("Search", "Sort"));

//		String prod = productPage.clickFirstProduct();
//		XL.setData("Search", prod);

		XL.setData("Search","Result", productPage.clickFirstProduct());

		productPage.checkPincode(XL.getData("Search", "Pincode"));
		System.out.println("pincode entered");
		System.out.println(productPage.getSpec());
		XL.setData("Search","Delivery Date", productPage.getDelDate());
		XL.setData("Search","Specification", productPage.getSpec());
		
		cartPage = productPage.addToCart();
		System.out.println("New Title : "+cartPage.getTitle());
		XL.setData("Search","Specification",cartPage.getPrice());
		
		cartPage.addProtection();
		cartPage.placeOrder();
		 
		
		
	}

//	To verify product is displayed in order history
//
//		@Test(dependsOnMethods= {"submitOrder"})
//		public void orderHistoryTest() {
//			
//			ProductCatalogue productCatalogue = landingPage.loginApplication("nandha@gmail.com", "N&a@1999");
//			OrderPage orderPage = productCatalogue.goToOrdersPage(); 
//			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
//			
//		}	

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
	public Object[][] getData1() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\nandha\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };// src\test\java\nandha\data\PurchaseOrder.json
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
