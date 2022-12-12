import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginRunner extends Base {
	LoginPage loginPage;
	WelcomePage welcomePage;

	String sheetName= "Login";
	ExcelReader ex = new ExcelReader("C:\\Users\\Atul\\IdeaProjects\\MyAccountAssignment\\src\\test\\resources\\LoginData.xlsx");

	@Test(description = "Login with valid username and password", enabled = true)
	public void ValidUsernamePassword() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(2000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 1, 0);
		String password = ex.getCellData(sheetName, 1, 1);
		welcomePage =loginPage.doLogin(username, password);
		Thread.sleep(2000);
		Assert.assertTrue(welcomePage.verifySignedIn());
	}
	@Test(description = "Login with incorrect username and incorrect password", enabled = true)
	public void incorrectUsernamePassword() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(2000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 2, 0);
		String password = ex.getCellData(sheetName, 2, 1);
		loginPage.doLogin(username, password);
		Thread.sleep(2000);
		String actualError = loginPage.getErrorDetails();
		Assert.assertEquals("Error: A user could not be found with this email address.",actualError);
	}
	@Test(description = "Login with correct username and empty password", enabled = true)
	public void correctUsernameEmptyPass() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(2000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 3, 0);
		String password = ex.getCellData(sheetName, 3, 1);
		loginPage.doLogin(username, password);
		Thread.sleep(2000);
		String actualError = loginPage.getErrorDetails();
        Assert.assertEquals("Error: Password is required.",actualError);
	}
	@Test(description = "Login with empty username and valid password", enabled = true)
	public void emptyUsernameValidPassword() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(2000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 4, 0);
		String password = ex.getCellData(sheetName, 4, 1);
		loginPage.doLogin(username, password);
		Thread.sleep(2000);
		String actualError =loginPage.getErrorDetails();
		Assert.assertEquals("Error: Username is required.",actualError);
	}
	@Test(description = "Login with empty username and empty password", enabled = true)
	public void emptyUsernameEmptyPassword() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(2000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 5, 0);
		String password = ex.getCellData(sheetName, 5, 1);
		loginPage.doLogin(username, password);
		Thread.sleep(2000);
		String actualError = loginPage.getErrorDetails();
		Assert.assertEquals("Error: Username is required.", actualError);
	}

	@Test(description = "Login Password should be masked", enabled = true)
	public void verifyPasswordType() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(3000);
		//Reading Credentials from Excel
		String password = ex.getCellData(sheetName, 6, 1);
		loginPage.setPassword(password);
		String passwordType = loginPage.verifyWebelementType("password");
		Thread.sleep(3000);
		Assert.assertEquals("password",passwordType);
	}
	@Test(description = "Login Handles case sensitive", enabled = true)
	public void verifyCaseSensitive() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(3000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 7, 0);
		String password = ex.getCellData(sheetName, 7, 1);
		loginPage.doLogin(username, password);
		Thread.sleep(3000);
		String actualError =loginPage.getErrorDetails();
		System.out.println(loginPage.getErrorDetails());
		Assert.assertEquals("Error: the password you entered for the username "+username+" is incorrect. Lost your password?",actualError);

	}
	@Test(description = "Login Authentication", enabled = true)
	public void verifyLoginAuthentication() throws Exception {
		loginPage = new LoginPage(driver);
		Thread.sleep(3000);
		//Reading Credentials from Excel
		String username = ex.getCellData(sheetName, 8, 0);
		String password = ex.getCellData(sheetName, 8, 1);
		welcomePage=loginPage.doLogin(username, password);
		loginPage =welcomePage.signOut();
		loginPage.clickBack();
		Thread.sleep(3000);
		boolean isLoginScreenDisplayed = driver.findElement(By.xpath("//input[@name='login']")).isDisplayed();
		Assert.assertTrue(isLoginScreenDisplayed);
	}

}