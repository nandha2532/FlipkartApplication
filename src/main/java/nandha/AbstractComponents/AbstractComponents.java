package nandha.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nandha.pageobjects.CartPage;
import nandha.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findByLoc)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

	wait.until(ExpectedConditions.visibilityOfElementLocated(findByLoc));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() {
		
		cartHeader.click();
		CartPage cartPage =  new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage() {
		
		orderHeader.click();
		OrderPage orderPage =  new OrderPage(driver);
		return orderPage;
	}
	
	
	
	public void javascriptExecutor(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
}
