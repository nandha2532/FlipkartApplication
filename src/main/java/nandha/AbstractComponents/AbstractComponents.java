package nandha.AbstractComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	protected WebElement cart;
	
//	Here's what the protected keyword signifies:
//
//	Access within the Same Package: Members marked as protected can be accessed by other classes within the same package.
//
//	Access in Subclasses: Subclasses (like ProductPage, which extends AbstractComponents) can access protected members directly.
	
	
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
	
	
	public void fluentWait(WebElement element){
		
		try {
	 // Set up FluentWait with 30-second timeout and polling interval of 2 seconds
    Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(4))
            .ignoring(NoSuchElementException.class);

    // Example usage: Wait until element with id "someElement" is visible
    wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	
	public void jsExec(WebElement element) {
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	public void jsScroll(WebElement element) {
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void moveToElement(WebElement element) {
		act.moveToElement(element).build().perform();
	}
	
	public void switchToTab(String window) {
		String parent = driver.getWindowHandle();
		if (!window.equalsIgnoreCase("parent")) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			// Switch to new tab
			for (String handle : allWindowHandles) {
				driver.switchTo().window(handle);
			}
		}
		else{
			driver.switchTo().window(parent);
		}
	}
	
	public void switchToMainTab() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		// Switch to new tab
		for (String handle : allWindowHandles) {
		    driver.switchTo().window(handle);
		}
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
