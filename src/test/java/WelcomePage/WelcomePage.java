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
	@Test
	public static void welcomePageURLCheck() {

		page_loaded();

		String welcomePageActualURL = driver.getCurrentUrl();

		String welcomePageExpectedURL = "https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w";

		Assert.assertEquals(welcomePageActualURL, welcomePageExpectedURL);

	}

	// method to check page title
	@Test
	public static void welcomePageTitleCheck() {

		page_loaded();

		String welcomePageTitleActual = driver.getTitle();

		String welcomePageTitleExpected = "Welcome";

		Assert.assertEquals(welcomePageTitleActual, welcomePageTitleExpected);

	}

	// method to check logo is present
	@Test
	public static void welcomePageLogoCheck() {

		page_loaded();

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Header Logo']")));

		WebElement logo = driver.findElement(By.xpath("//img[@alt='Header Logo']"));

		Boolean logoPresent = logo.isDisplayed();

		System.out.println(logoPresent);

	}

	// method to check verify help link is present.
	@Test
	public static void VerifyNeedHelpLink() {

		page_loaded();

		String NeedHelpTextActual = driver.findElement(By.xpath("//div[contains(text(),'Need help?')]")).getText();

		String NeedHelpTextExpected = "Need help";

		Assert.assertEquals(NeedHelpTextActual, NeedHelpTextExpected);

	}

	// method to check welcome page heading and sub heading showing or not
	@Test
	public static void welcomePageElementsShowing() {

		page_loaded();

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

	@Test
	public static void welcomePageSubmitButtonWorking() throws InterruptedException {

		page_loaded();

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

	@Test(dependsOnMethods = { "welcomePageSubmitButtonWorking" })
	public static void applicantTypePageElementsandvalidationCheck() throws InterruptedException {

		String headingExpected = "Applicant type";

		WebElement headingWeb_e = driver
				.findElement(By.xpath("//div[contains(@class,'truncate !text-xl font-medium')]"));

		String headingShowingActual = headingWeb_e.getText();

		//System.out.println(headingShowingActual);

		Assert.assertEquals(headingShowingActual, headingExpected);

		driver.findElement(By.xpath("//div[contains(text(),'Employee')]")).isDisplayed();
		
		driver.findElement(By.xpath("//div[contains(text(),'Spouse')]")).isDisplayed();

		driver.findElement(By.xpath("//div[@class='mr-1']")).click();

		WebElement validationMessageApplicantPage_e = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']"));

		String validationMessageApplicantPageActual = validationMessageApplicantPage_e.getText();

		Thread.sleep(1000);

		//System.out.println(validationMessageApplicantPage);
		String validationMessageApplicantPageExpected = "Please make a selection";
		
		Assert.assertEquals(validationMessageApplicantPageActual, validationMessageApplicantPageExpected);
		
		}

}
