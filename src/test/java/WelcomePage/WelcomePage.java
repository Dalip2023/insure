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

		Boolean logoPresent_a = logo.isDisplayed();

		Boolean logoPresent_e = true;

		Assert.assertEquals(logoPresent_a, logoPresent_e);

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

	// back button showing or not on proceeding application
	@Test(dependsOnMethods = { "welcomePageSubmitButtonWorking" }, priority = 7)
	public static void backbuttonshowing() {

		Boolean SubmitButtonShowing_e = true;
		Boolean SubmitButtonShowing_a = driver.findElement(By.xpath(
				"//div[contains(@class,'flex items-center justify-center mt-12')]//button[contains(@type,'button')]"))
				.isDisplayed();
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

	// method to check applicant Type Page submission
	@Test(dependsOnMethods = { "applicantTypePageElementsandvalidationCheck" }, priority = 9)
	public static void applicantTypePageSubmission() throws InterruptedException {

		driver.findElement(By.xpath("//div[contains(text(),'Employee')]")).click();

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Supplemental Life')]")));

		Thread.sleep(2000);

	}

	// method to check product page elements and validation check
	@Test(dependsOnMethods = { "applicantTypePageSubmission" }, priority = 10)
	public static void productPageElementsandValidationCheck() throws InterruptedException {

		String ProductPageHeading_a = driver.findElement(By.xpath("//div[@class='truncate !text-xl font-medium ']"))
				.getText();

		String productPageHeading_e = "Product";

		Assert.assertEquals(ProductPageHeading_a, productPageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']//div[@class='flex items-center justify-center ']"))
				.click();

		String validationMessageProductPage_e = "Please make a selection";

		String validationMessageProductPage_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']")).getText();

		Assert.assertEquals(validationMessageProductPage_a, validationMessageProductPage_e);

		Thread.sleep(2000);
	}

	// method to check product page submission
	@Test(dependsOnMethods = { "productPageElementsandValidationCheck" }, priority = 11)
	public static void productPageSubmission() throws InterruptedException {

		driver.findElement(By.xpath("//div[contains(text(),'Supplemental Life')]")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='First']")));

		Thread.sleep(2000);

	}

	// method to check Name Page elements and validation check
	@Test(dependsOnMethods = { "productPageSubmission" }, priority = 12)
	public static void namePageElementsandValidationCheck() throws InterruptedException {

		String namePageHeading_a = driver
				.findElement(By.xpath("//div[@class='truncate !text-xl text-neutral-100 font-medium ']")).getText();

		String namePageHeading_e = "Your name";

		Assert.assertEquals(namePageHeading_a, namePageHeading_e);

		driver.findElement(By.xpath("//label[normalize-space()='First']")).isDisplayed();

		driver.findElement(By.xpath("//label[normalize-space()='Last']")).isDisplayed();

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String validationMessageNamePagefname_e = "Please fill this in";
		String validationMessageNamePagelname_e = "Please fill this in";

		String validationMessageNamePagefname_a = driver
				.findElement(By.xpath("//div[contains(@class,'grid grid-cols-2 gap-4')]//div[2]//div[1]//span[1]"))
				.getText();

		String validationMessageNamePagelname_a = driver
				.findElement(By.xpath("//div[contains(@class,'grid grid-cols-2 gap-4')]//div[2]//div[1]//span[1]"))
				.getText();

		Assert.assertEquals(validationMessageNamePagefname_a, validationMessageNamePagefname_e);
		Assert.assertEquals(validationMessageNamePagelname_a, validationMessageNamePagelname_e);

		Thread.sleep(2000);

	}

	// method to check Name Page submission check
	@Test(dependsOnMethods = { "namePageElementsandValidationCheck" }, priority = 13)
	public static void namePageSubmission() throws InterruptedException {

		WebElement fname_field = driver.findElement(By.xpath("//input[@id='first_name']"));

		WebElement lname_field = driver.findElement(By.xpath("//input[@id='last_name']"));

		fname_field.sendKeys("dalip");

		Thread.sleep(1000);

		lname_field.sendKeys("kumar");

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='answer']")));

		Thread.sleep(2000);

	}

	// Method to check continueApplicationButton and Start new application button
	// showing on reload/ next visit or not
	// @Test(dependsOnMethods = { "welcomePageSubmitButtonWorking" }, priority = 9)
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

	// @Test(dependsOnMethods = { "reloadorRevisitContinueApplicationButton" },
	// priority = 10)
	public static void reloadorRevisitStartNewApplicationButton() {

		// new application button
		Boolean newApplicationbutton_showing_a = driver
				.findElement(By.xpath("// div[contains(text(),'Start a new application')]")).isDisplayed();
		Boolean newApplicationbutton_showing_e = true;

		Assert.assertEquals(newApplicationbutton_showing_a, newApplicationbutton_showing_e);

	}

}
