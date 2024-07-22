package nandha.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import nandha.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver; // this.driver refers to the driver created here ****second driver refers to
								// driver from standalone test
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='PWd9A7 xvz6eC']")
	WebElement price;
	
	@FindBy(xpath = "//*[text()='Add Item']")
	WebElement addProtect;
	
	@FindBy(xpath = "//*[text()='Place Order']")
	WebElement placeOrder;
	
	public String getPrice() {
		return price.getText();
	}
	
	public void  addProtection() {
		addProtect.click();
	}
	
	public void  placeOrder() {
		placeOrder.click();
	}


}
