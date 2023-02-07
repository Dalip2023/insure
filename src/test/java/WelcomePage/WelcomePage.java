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

	@Test
	public static void welcomePageTitleCheck() {

		driver.get("https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w");

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		String welcomePageTitleActual = driver.getTitle();

		String welcomePageTitleExpected = "Welcome";

		Assert.assertEquals(welcomePageTitleActual, welcomePageTitleExpected);

	}

	@Test
	public static void welcomePageLogoCheck() {

		driver.get("https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w");

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Header Logo']")));

		WebElement logo = driver.findElement(By.xpath("//img[@alt='Header Logo']"));

		Boolean logoPresent = logo.isDisplayed();

		System.out.println(logoPresent);

	}

	public static void VerifyNeedHelpLink() {

		driver.get("https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w");

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		String NeedHelpTextActual = driver.findElement(By.xpath("//div[contains(text(),'Need help?')]")).getText();

		String NeedHelpTextExpected = "Need help";

		Assert.assertEquals(NeedHelpTextActual, NeedHelpTextExpected);

	}

	@Test
	public static void welcomePageTextShowing() {

		driver.get("https://d3j8nuwp74eyml.cloudfront.net/5U5PU/S2xbn/UGFnZV8w");

		WebDriverWait Welcome_page = new WebDriverWait(driver, 30);
		Welcome_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		String textwelcomeActual = driver
				.findElement(By.xpath("//div[@class='text-2xl text-neutral-100 text-center font-medium mb-4']"))
				.getText();
		System.out.println(textwelcomeActual);

		String textwelcomeExpected = "?Welcome";

		Assert.assertEquals(textwelcomeActual, textwelcomeExpected);

	}

}
