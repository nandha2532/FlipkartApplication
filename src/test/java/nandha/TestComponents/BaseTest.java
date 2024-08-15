package nandha.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nandha.AbstractComponents.AbstractComponents;
import nandha.pages.HomePage;

public class BaseTest {

	public WebDriver driver; // if driver is initialised in the if condition it's scope is limited "WebDriver
								// driver = new ChromeDriver();"
	// so to make scope driver is initialised here and --> WebDriver removed in
	// declaration "driver = new ChromeDriver();"

	public AbstractComponents abs;// Public -->so otehr classes can access
	public HomePage homePage;

//	@SuppressWarnings("deprecation")
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\nandha\\resources\\GlobalData.properties");
		prop.load(fis);
//		String browserName = prop.getProperty("browser");
//		System.getProperty(browser)!= null;
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"):prop.getProperty("browser");
		
//		condition != null ? "Condition - true: this will execute" :  "Condition - false: this will execute"
		

//		String browserName = AbstractComponents.getProp("browser");

		System.out.println(browserName);
//		if (browserName.equalsIgnoreCase("Chrome")) {
//			driver = new ChromeDriver();
//
//		}
		if (browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			
//			in headless mode to open browser in full screen mode use the set size
			driver.manage().window().setSize(new Dimension(1400,900));

		}

		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		return driver;

	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

//		to assign life to the driver --> WebDriver driver ->get it from listener

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File filePAth = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, filePAth);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// reading the json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// string to Hashmap -->JackSon Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public HomePage launchApplication() throws IOException {

		driver = initializeDriver();
		driver.get(AbstractComponents.getProp("url"));
		abs = new AbstractComponents(driver);
		homePage = new HomePage(driver); // send driver as argument to the LandingPage()
		return homePage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
//		driver.close();	
	}
}
