package CommonLib;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.google.j2objc.annotations.ReflectionSupport.Level;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonLib {

	public static WebDriver driver = null;

	// code for running selenium tests on browser stack cloud.
	// public static final String USERNAME = "tarun156";
	// public static final String AUTOMATE_KEY = "Vq4cZ9X7qrKGVjcGjvbL";
	// public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY +
	// "@hub-cloud.browserstack.com/wd/hub";

	public static void LaunchBrowser() throws InterruptedException, MalformedURLException {

//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("os", "Windows");
//		caps.setCapability("os_version", "10");
//		caps.setCapability("browser", "Chrome");
//		caps.setCapability("browser_version", "87.0");
//		caps.setCapability("name", "New calc");
//		caps.setCapability("browserstack.local", "false");
//		caps.setCapability("name", "logic jump tests");
//		driver = new RemoteWebDriver(new java.net.URL(URL), caps);

		// BasicConfigurator.configure();
		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver();

		WebDriverManager.chromedriver().setup();

		/*
		 * to run the script headless
		 * 
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--headless"); driver = new ChromeDriver(options);
		 * 
		 * to run the script headless
		 */

		// driver = new ChromeDriver();
		ChromeOptions handlingSSL = new ChromeOptions();
		// Using the accept insecure certificate method with true as parameter to accept
		// untrusted certificate
		handlingSSL.setAcceptInsecureCerts(true);

		// Creating instance of Chrome driver by passing reference of ChromeOptions
		driver = new ChromeDriver(handlingSSL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.manage().window().fullscreen();
		// driver.manage().window().setSize(new Dimension(360, 600));
		driver.manage().deleteAllCookies();
	}

	public static void closeBrowser() throws InterruptedException {
		driver.close();
	}

	public static void quitDriver() throws InterruptedException {
		driver.quit();
	}

	
	public static void scrolling_to_bottom() {
		JavascriptExecutor js2 = ((JavascriptExecutor) driver);
		js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrolling_to_top() {
		JavascriptExecutor js2 = ((JavascriptExecutor) driver);
		js2.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public static void openNewTab() {
		((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
	}

	public static void switchToNewTab() {
		// openNewTab();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		java.util.Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
	}

	public static void resizeToNormalWindow() throws InterruptedException {
		driver.manage().window().maximize();
		Thread.sleep(2000);

	}
}
