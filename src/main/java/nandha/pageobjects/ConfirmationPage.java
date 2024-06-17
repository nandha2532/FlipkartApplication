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

public class ConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)// getting driver from Stand Alone Test
	{
		//Initialization
		super(driver);
		this.driver = driver; // thisy.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this); 
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement message;

	
	public String confirmMessage() {
//		String confirmMessage = message.getText();
		return message.getText();
	}
}

