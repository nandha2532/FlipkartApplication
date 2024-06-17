package nandha.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nandha.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)// getting driver from Stand Alone Test
	{
		//Initialization
		super(driver);
		this.driver = driver; // this.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this); 
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".even h3")
	List<WebElement> cartProducts;  // Since findElements-->List<WebElement>
	
	@FindBy(xpath="//*[@class='totalRow']/button")
	WebElement checkOut;
	
	public Boolean VerifyProductDisplay(String productName) {
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
//		anyMatch --> returns a boolean value
	}
	
	public CheckOutPage checkOut() {
		
		javascriptExecutor(checkOut);
//		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return new CheckOutPage(driver);
	}
	
}

