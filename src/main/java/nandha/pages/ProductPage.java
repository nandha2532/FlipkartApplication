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

public class ProductPage extends AbstractComponents {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver; // this.driver refers to the driver created here ****second driver refers to
								// driver from standalone test
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(@class,'sHCOk2')]/div")
	List<WebElement> sort;

	@FindBy(xpath = "//*[contains(@class,'TSD49J')]")
	List<WebElement> catergory;

	@FindBy(xpath = "//*[contains(@class,'jBYtJt')]")
	List<WebElement> drpdown;

	@FindBy(xpath = "//*[contains(@class,'Pke_EE')]")
	WebElement search;

	@FindBy(xpath = "//*[contains(@type,'submit')]")
	WebElement searchIcon;

	// div[@class='suthUA'] //*[@fdprocessedid='wa11pi']

	@FindBy(xpath = "//div[@class='suthUA']/select")
	WebElement min;

	// *[@fdprocessedid='4m52kd']
	@FindBy(xpath = "//div[@class='tKgS7w']/select")
	WebElement max;

	@FindBy(xpath = "//*[text()='Customer Ratings']")
	WebElement ratingDrp;

	@FindBy(xpath = "//*[text()='Offers']")
	WebElement offersDrp;

	// *[text()='Discount']/ancestor::section
	@FindBy(xpath = "//*[@class='fxf7w6 rgHxCQ' and text()='Discount']")
	WebElement discountDrp;
	
	@FindBy(xpath = "//*[text()='Availability']")
//	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[23]/div")
	WebElement availDrp;
	
//	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[23]/div[2]/div/div/div/label/div[1]")
	@FindBy(xpath = "//*[contains(text(),'Out of Stock')]/preceding-sibling:: div")
	WebElement availCheck;

	@FindBy(xpath = "//*[contains(@class,'KzDlHZ')]")
	List<WebElement> prodName;

	@FindBy(xpath = "(//*[contains(@class,'KzDlHZ')])[1]")
	WebElement firstProduct;
	
	@FindBy(css = "#pincodeInputId")
	WebElement pincode;
	
	@FindBy(css = ".i40dM4")
	WebElement check;
	
	@FindBy(css = ".Y8v7Fl")
	WebElement delDate;
	
	@FindBy(xpath = "//*[text()='Highlights']/..")
	WebElement spec;
	
	@FindBy(xpath = "//*[text()='Add to cart']")
	WebElement addCart;
	
	@FindBy(xpath = "//*[text()='Buy Now']")
	WebElement buyNow;
	
	

	public void sort(String search) {
		filterFromList(sort, search).click();
	}

	public void moveTocategory(String search) {
		moveToElement(filterFromList(catergory, search));
	}

	public void clickonCategory(String search) {
		filterFromList(drpdown, search).click();
	}

	public void priceMin(String value) {
		selectByValue(min, value);
	}

	public void priceMax(String value) {
		selectByValue(max, value);
	}

	public void rating(String rating) {
		act.moveToElement(ratingDrp);
		waitForWebElementToAppear(ratingDrp);
		boolean rate = driver.findElement(By.xpath("//*[text()='" + rating + "★ & above']/preceding-sibling:: div"))
				.isDisplayed();

		if (!rate) {
			ratingDrp.click();
		}

		// *[text()='4★ & above']/preceding-sibling:: div
		waitForWebElementToAppear(driver.findElement(By.xpath("//*[text()='" + rating + "★ & above']/preceding-sibling:: div")));
		driver.findElement(By.xpath("//*[text()='" + rating + "★ & above']/preceding-sibling:: div")).click();
	}

	public void offers(String offer) throws InterruptedException {
		act.moveToElement(offersDrp);
		waitForWebElementToAppear(offersDrp);
		Thread.sleep(1000);
		boolean off = driver.findElement(By.xpath("//*[text()='" + offer + "']/preceding-sibling:: div")).isDisplayed();

		if (!off) {
			offersDrp.click();
		}
		// *[text()='4★ & above']/preceding-sibling:: div
		driver.findElement(By.xpath("//*[text()='" + offer + "']/preceding-sibling:: div")).click();
	}

	public void discount(String disc) throws InterruptedException {
		System.out.println("select rating :" + disc);
		act.moveToElement(discountDrp);
		waitForWebElementToAppear(discountDrp);
		boolean off = driver.findElement(By.xpath("//*[text()='" + disc + "']/preceding-sibling:: div")).isDisplayed();

		if (!off) {
			discountDrp.click();
		}
//		Thread.sleep(1500);
		waitTillClick(By.xpath("//*[text()='" + disc + "']/preceding-sibling:: div"));
		// *[text()='4★ & above']/preceding-sibling:: div
		jsExec(driver.findElement(By.xpath("//*[text()='" + disc + "']/preceding-sibling:: div")));
//		driver.findElement(By.xpath("//*[text()='"+disc+"']/preceding-sibling:: div")).click();
		System.out.println("select rating");
		

	}

	public void availability() {
		jsScroll(availDrp);

		try {
			jsExec(availCheck);
		} catch (Exception e) {
			jsExec(availDrp);
			waitForWebElementToAppear(availCheck);
			jsExec(availCheck);
		}
	}

	public void clickProduct(String product) {
		filterFromList(prodName, product).click();
	}

	public String clickFirstProduct() {
		waitForWebElementToAppear(firstProduct);
		String product = firstProduct.getText();
		firstProduct.click();
		switchToTab("Child");
		fluentWait(cart);
		return product;
	}

	public void checkPincode(String pin) {
		
		try {
		pincode.sendKeys(pin);
		}catch(Exception e) {
			System.out.println("Entered Pincode : "+pincode);
			jsExec(pincode);
			pincode.sendKeys(pin);
		}
		check.click();
	}

	public String getDelDate() {
		fluentWait(delDate);
//		waitForWebElementToAppear(delDate);
		return delDate.getText();
	}

	public String getSpec() {
		return spec.getText();
	}

	public cartPage addToCart() {
		addCart.click();
		cartPage cartPage = new cartPage(driver);
		return cartPage;
	}
	
}
