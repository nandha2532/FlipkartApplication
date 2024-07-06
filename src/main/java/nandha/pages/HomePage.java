package nandha.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nandha.AbstractComponents.AbstractComponents;

public class HomePage extends AbstractComponents {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver; // this.driver refers to the driver created here ****second driver refers to driver from standalone test
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Login']")
	WebElement loginLink;
	
	@FindBy(xpath="//*[contains(@class,'_1ch8e_')]")
	List<WebElement> header;
	
	@FindBy(xpath="//*[contains(@class,'_1BJVlg')]")
	List<WebElement> drpdown1;
	
	@FindBy(xpath="//*[contains(@class,'_3490ry')]")
	List<WebElement> drpdown2;
	


	public void goToLogin() {
		loginLink.click();
	}
	
	public void clickMainCatergory(String search) {
		filterFromList(header,search).click();
	}
	
	public void moveTocategory(String search) {
		moveToElement(filterFromList(drpdown1,search));
	}
	
	public void clickonCategory(String search) {
		filterFromList(drpdown1,search).click();
	}



}
