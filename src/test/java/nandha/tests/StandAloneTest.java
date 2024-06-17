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

import nandha.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		String countryName = "India";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("nandha@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("N&a@1999");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		Parent to child traverse and :last of type --> gives the last match item
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		#--> to use id in CSS /  .-->to use classname in CSS
//		ng-animating
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".even h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
//		anyMatch --> returns a boolean value
//		filter will return a complete webelement ( value/string )
		
		Assert.assertTrue(match);
//		Thread.sleep(3000);
		
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".totalRow button")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='totalRow']/button"))));
//		Thread.sleep(3000);
		WebElement checkOut=driver.findElement(By.xpath("//*[@class='totalRow']/button"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", checkOut);
		driver.findElement(By.cssSelector("[placeholder*='Select Country']")).sendKeys(countryName);
		Actions a = new Actions(driver);
//		wait.until(ExpectedConditions.elementToBeClickable(By.className("ta-results")));
////		driver.findElement(By.xpath("//*[contains(@class,'ta-results')]/button/span/li")).getText();
//		Thread.sleep(5000);
//		a.moveToElement(driver.findElement(By.cssSelector("[placeholder*='Select Country']"))).sendKeys(countryName).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(".ta-results")));
		driver.findElement(By.xpath("(//*[contains(@class,'ta-item')])[2]")).click();
//		a.sendKeys(Keys.ENTER).build().perform();
//		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit"))).click();
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click()", placeOrder);
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}
	


}
