package nandha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nandha.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver; // this.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[contains(@class,'r4vIwl')]")
	WebElement email;
	
	@FindBy(xpath = "//*[contains(@class,'QqFHMw')]")
	WebElement reqOTP;
	
	public void enterEmail() {
		email.sendKeys("useremail");
	}
	public void requestOtp() {
		reqOTP.click();
	}
	
	

	
}
