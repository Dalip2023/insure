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

		String welcomePageTitleActual = driver.getCurrentUrl();

		String welcomePageTitleExpected = "https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w";
		
		Assert.assertEquals(welcomePageTitleActual, welcomePageTitleExpected);

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

	// method to check welcome page heading showing or not
	@Test
	public static void welcomePageTextShowing() {

		page_loaded();

		String textwelcomeActual = driver
				.findElement(By.xpath("//div[@class='text-2xl text-neutral-100 text-center font-medium mb-4']"))
				.getText();
		System.out.println(textwelcomeActual);

		String textwelcomeExpected = "?Welcome";

		Assert.assertEquals(textwelcomeActual, textwelcomeExpected);

	}

}
