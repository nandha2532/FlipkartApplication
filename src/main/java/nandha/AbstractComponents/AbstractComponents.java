package nandha.AbstractComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import nandha.pageobjects.CartPage;
import nandha.pageobjects.OrderPage;
import nandha.pages.ProductPage;

public class AbstractComponents{
	
	WebDriver driver;
	public Actions act;
	JavascriptExecutor js;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.out.println(getProp("url"));
	}

	public AbstractComponents(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this); 
		this.act = new Actions(driver); 
	}
	
	@FindBy(xpath="//*[text()='Cart']")
	WebElement cart;
	
	@FindBy(xpath= "//*[contains(@placeholder,'Search for')]")
	WebElement search;
	
	@FindBy(xpath= "//*[contains(@type,'submit')]")
	WebElement searchIcon;
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void waitForElementToAppear(By findByLoc)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));

	wait.until(ExpectedConditions.visibilityOfElementLocated(findByLoc));
	}
	
	public void waitTillClick(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));

	wait.until(ExpectedConditions.elementToBeClickable(findBy));
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

	
	public void jsExec(WebElement element) {
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public void moveToElement(WebElement element) {
		act.moveToElement(element).build().perform();
	}
	
//	Actions act = new Actions(driver);
	
	public WebElement filterFromList(List<WebElement> products,String productName) {
		WebElement prod = products.stream().filter(product->
		product.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public static String getProp(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\nandha\\resources\\GlobalData.properties");
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
	
	public void goToCart() {
		cart.click();
	}
	
	public ProductPage search(String product) {
		search.sendKeys(product);
//		search.sendKeys(Keys.ENTER);
		searchIcon.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	public void selectByValue(WebElement element,String value) {
		Select select =new Select(element);
		select.selectByValue(value);
	}
	

	
}
