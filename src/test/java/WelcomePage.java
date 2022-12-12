import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage {
	private final WebDriver driver;

	public WelcomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifySignedIn(){
		//Verifying if user is signed in or not using sign out button.
		return driver.findElement(By.linkText("Sign Out")).isDisplayed();
	}

	public LoginPage signOut(){
		driver.findElement(By.linkText("Sign out")).click();
		return new LoginPage(driver);
	}

}