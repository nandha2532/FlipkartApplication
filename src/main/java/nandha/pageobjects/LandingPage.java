package nandha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nandha.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)// getting driver from BsseTest
	{
		//Initialization
		super(driver);
		this.driver = driver; // this.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this); 
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail")); // This step is converted using Pagefactory for simplification
	
	// Page Factory
	
	@FindBy(id="userEmail")
	WebElement userEmail; 
	
	@FindBy(id="userPassword")
	WebElement userPassword; 
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="flyInOut")
	WebElement errorMessage; 
	
	public ProductCatalogue loginApplication(String email,String password) 
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessaage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
