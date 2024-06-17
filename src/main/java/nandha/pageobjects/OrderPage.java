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

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver)// getting driver from Stand Alone Test
	{
		//Initialization
		super(driver);
		this.driver = driver; // thisy.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this); 
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productNames;  // Since findElements-->List<WebElement>
	
	
	public Boolean VerifyOrderDisplay(String productName) {
		
		Boolean match = productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		return match;
//		anyMatch --> returns a boolean value
	}
	
	
}

