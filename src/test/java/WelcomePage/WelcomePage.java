package WelcomePage;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import CommonLib.CommonLib;

public class WelcomePage extends CommonLib {

	@BeforeClass
	public static void open_browser() throws InterruptedException, MalformedURLException {
		CommonLib.LaunchBrowser();
	}

	@AfterClass
	public static void quit_browser() throws InterruptedException {
		CommonLib.closeBrowser();
		CommonLib.quitDriver();
	}

	public static void page_loaded() {
		driver.get("https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w");

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
	}

	// method to check desired url opened or not?
	@Test(priority = 1)
	public static void welcomePageURLCheck() {

		page_loaded();

		String welcomePageActualURL = driver.getCurrentUrl();

		String welcomePageExpectedURL = "https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w";

		Assert.assertEquals(welcomePageActualURL, welcomePageExpectedURL);

	}

	// method to check page title
	@Test(dependsOnMethods = { "welcomePageURLCheck" }, priority = 2)
	public static void welcomePageTitleCheck() {

		String welcomePageTitleActual = driver.getTitle();

		String welcomePageTitleExpected = "Welcome";

		Assert.assertEquals(welcomePageTitleActual, welcomePageTitleExpected);

	}

	// method to check logo is present
	@Test(priority = 3)
	public static void welcomePageLogoCheck() {

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Header Logo']")));

		WebElement logo = driver.findElement(By.xpath("//img[@alt='Header Logo']"));

		Boolean logoPresent = logo.isDisplayed();

		System.out.println(logoPresent);

	}

	// method to check verify help link is present.
	@Test(priority = 4)
	public static void VerifyNeedHelpLink() {

		String NeedHelpTextActual = driver.findElement(By.xpath("//div[contains(text(),'Need help?')]")).getText();

		String NeedHelpTextExpected = "Need help?";

		Assert.assertEquals(NeedHelpTextActual, NeedHelpTextExpected);

	}

	// method to check welcome page heading
	@Test(priority = 5)
	public static void welcomePageElementsShowing() {

		String textwelcomeActual = driver
				.findElement(By.xpath("//div[@class='text-2xl text-neutral-100 text-center font-medium mb-4']"))
				.getText();

		String textwelcometextExpected = "Welcome";
		String HeadingActual = textwelcomeActual.substring(2);

		Assert.assertEquals(HeadingActual, textwelcometextExpected);

		WebElement SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		Boolean SubmitButtonShowing = SubmitButton.isDisplayed();
		Boolean SubmitButtonShowingExpected = true;

		Assert.assertEquals(SubmitButtonShowing, SubmitButtonShowingExpected);

	}

	// Method to check welcome page submit button working.
	@Test(priority = 6)
	public static void welcomePageSubmitButtonWorking() throws InterruptedException {

		// page_loaded();

		Thread.sleep(2000);

		WebElement SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		SubmitButton.click();

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'truncate !text-xl font-medium')]")));

		Thread.sleep(2000);

		String applicantPageActualURL = driver.getCurrentUrl();

		String applicantPageExpectedURL = "https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8x";

		Assert.assertEquals(applicantPageActualURL, applicantPageExpectedURL);

		String applicantPageTitleActual = driver.getTitle();

		String applicantPageTitleExpected = "Applicant Type";

		Assert.assertEquals(applicantPageTitleActual, applicantPageTitleExpected);

	}

	@Test(dependsOnMethods = { "welcomePageSubmitButtonWorking" }, priority = 7)
	public static void backbuttonshowing() {

		Boolean SubmitButtonShowing_e = true;
		Boolean SubmitButtonShowing_a = driver.findElement(By.xpath("//div[contains(@class,'flex items-center justify-center mt-12')]//button[contains(@type,'button')]")).isDisplayed();
		Assert.assertEquals(SubmitButtonShowing_a, SubmitButtonShowing_e);

	}

	// Method to check page heading, web element and validation of applicantType.
	@Test(dependsOnMethods = { "welcomePageSubmitButtonWorking" }, priority = 8)
	public static void applicantTypePageElementsandvalidationCheck() throws InterruptedException {

		String headingExpected = "Applicant type";

		WebElement headingWeb_e = driver
				.findElement(By.xpath("//div[contains(@class,'truncate !text-xl font-medium')]"));

		String headingShowingActual = headingWeb_e.getText();

		// System.out.println(headingShowingActual);

		Assert.assertEquals(headingShowingActual, headingExpected);

		driver.findElement(By.xpath("//div[contains(text(),'Employee')]")).isDisplayed();

		driver.findElement(By.xpath("//div[contains(text(),'Spouse')]")).isDisplayed();

		driver.findElement(By.xpath("//div[@class='mr-1']")).click();

		WebElement validationMessageApplicantPage_e = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']"));

		String validationMessageApplicantPageActual = validationMessageApplicantPage_e.getText();

		Thread.sleep(1000);

		// System.out.println(validationMessageApplicantPage);
		String validationMessageApplicantPageExpected = "Please make a selection";

		Assert.assertEquals(validationMessageApplicantPageActual, validationMessageApplicantPageExpected);

	}

	// Method to check continueApplicationButton and Start new application button
	// showing on reload/ next visit or not
	@Test(dependsOnMethods = { "welcomePageSubmitButtonWorking" }, priority = 9)
	public static void reloadorRevisitContinueApplicationButton() throws InterruptedException {

		Thread.sleep(1000);

		driver.navigate().refresh();

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Continue application')]")));

		Thread.sleep(2000);

		// page heading
		String SelectApplicationHeading_e = "Welcome Back";
		String SelectApplicationHeading = driver.findElement(By.xpath("//div[@class='text-2xl text-neutral-100']"))
				.getText();
		String SelectApplicationHeading_a = SelectApplicationHeading.substring(3);
		
		System.out.println(SelectApplicationHeading_a);

		// continue button
		Boolean continuebutton_showing_a = driver
				.findElement(By.xpath("//div[contains(text(),'Continue application')]")).isDisplayed();
		Boolean continuebutton_showing_e = true;

		// page title
		String Expectedtitle_Selectapplication = "Select Application";
		String actualTitle_selectapplication = driver.getTitle();

		// Assertions
		Assert.assertEquals(actualTitle_selectapplication, Expectedtitle_Selectapplication);
		Assert.assertEquals(continuebutton_showing_a, continuebutton_showing_e);
		Assert.assertEquals(SelectApplicationHeading_a, SelectApplicationHeading_e);

	}

	@Test(dependsOnMethods = { "reloadorRevisitContinueApplicationButton" }, priority = 10)
	public static void reloadorRevisitStartNewApplicationButton() {

		// new application button
		Boolean newApplicationbutton_showing_a = driver
				.findElement(By.xpath("// div[contains(text(),'Start a new application')]")).isDisplayed();
		Boolean newApplicationbutton_showing_e = true;

		Assert.assertEquals(newApplicationbutton_showing_a, newApplicationbutton_showing_e);

	}

}
