import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class Base {
	public static WebDriver driver;
	public static String URL= "http://practice.automationtesting.in/";

	@BeforeMethod // Pre-Conditions
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Atul\\IdeaProjects\\MyAccountAssignment\\src\\test\\resources\\chromedriver.exe");
		ChromeOptions cp = new ChromeOptions();
		cp.addArguments("--disable-notifications");
		cp.addArguments("--incognito");
		driver = new ChromeDriver(cp); // Starts Chrome Browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.linkText("My Account")).click();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}