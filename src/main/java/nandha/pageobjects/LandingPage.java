package nandha.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
	
	public void goTo() throws IOException {
		driver.get(getProp("url"));
	}
}
