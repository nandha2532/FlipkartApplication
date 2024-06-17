package nandha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.testandquiz.com/selenium/testing.html");
		
		Select dropdown = new  Select(driver.findElement(By.id("testingdropdown")));
		dropdown.selectByVisibleText("Database Testing");
		driver.close();
		
	}
}
