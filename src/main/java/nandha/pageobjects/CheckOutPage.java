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

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)// getting driver from Stand Alone Test
	{
		//Initialization
		super(driver);
		this.driver = driver; // thisy.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this); 
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css="[placeholder*='Select Country']")
	WebElement country;
	
	By dropDown = By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//*[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitOrder;

	
	public void selectCountry(String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(dropDown);
		selectCountry.click();
//		javascriptExecutor(selectCountry);
	}
	
	public ConfirmationPage placeOrder() {
		javascriptExecutor(submitOrder);
//		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return new ConfirmationPage(driver);
		
	}
	
}

