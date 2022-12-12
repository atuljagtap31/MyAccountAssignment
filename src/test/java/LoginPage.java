import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private final WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public By userName = By.id("username");
	public By password = By.id("password");
	public By loginButton = By.xpath("//input[@name='login']");

	public By errorMessage =By.xpath("//div[@id='content']//li");

	public void setUserName(String user) {
			driver.findElement(userName).click();
			driver.findElement(userName).clear();
			driver.findElement(userName).sendKeys(user);
	}

	public void setPassword(String pass) {
		driver.findElement(password).click();
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);

	}

	public void clickLogin() {
		driver.findElement(loginButton).click();

	}

   public String getErrorDetails(){
		String error= driver.findElement(errorMessage).getText();
        System.out.println(error);
	   return error;
   }

   public String verifyWebelementType(String webElement){
		String type="";
		switch (webElement){
			case "password":
				type = driver.findElement(password).getAttribute("type");
				break;
			default:
				System.out.println("Please enter correct webElement");
		}
		return type;

   }

   public void clickBack(){
		driver.navigate().back();
   }

	public WelcomePage doLogin(String user, String pass) {
		setUserName(user);
		setPassword(pass);
		clickLogin();
		return new WelcomePage(driver);
	}
}