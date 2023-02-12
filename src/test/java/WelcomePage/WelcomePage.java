package WelcomePage;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

		WebDriverWait product_page = new WebDriverWait(driver, 30);
		product_page.until(
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

		WebDriverWait name_page = new WebDriverWait(driver, 30);
		name_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='First']")));

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

		WebDriverWait email_page = new WebDriverWait(driver, 30);
		email_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='answer']")));

		Thread.sleep(2000);

	}

	// method to check Email Page elements and validation check
	@Test(dependsOnMethods = { "productPageSubmission" }, priority = 14)
	public static void emailPageElementsandValidationCheck() throws InterruptedException {

		String emailPageHeading_a = driver
				.findElement(By.xpath("//div[@class='text-xl text-neutral-100 font-medium mb-8']")).getText();

		String emailPageHeading_e = "Email";

		Assert.assertEquals(emailPageHeading_a, emailPageHeading_e);

		driver.findElement(By.xpath("//input[@name='answer']")).isDisplayed();

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String validationMessageEmail_e = "Please fill this in";

		String validationMessageEmail_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please fill this in']")).getText();

		Assert.assertEquals(validationMessageEmail_a, validationMessageEmail_e);

		Thread.sleep(2000);

	}

	// method to check Name Page submission check
	@Test(dependsOnMethods = { "emailPageElementsandValidationCheck" }, priority = 15)
	public static void emailPageSubmission() throws InterruptedException {

		WebElement email_field = driver.findElement(By.xpath("//input[@name='answer']"));

		email_field.sendKeys("dalip@test.com");

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait coverage_page = new WebDriverWait(driver, 30);
		coverage_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='px-2']")));

		Thread.sleep(2000);

	}

	// method to check coveragePage Elements and validations
	@Test(dependsOnMethods = { "emailPageSubmission" }, priority = 16)
	public static void coveragePageElementsCheck() throws InterruptedException {

		driver.findElement(By.xpath("//div[@class='px-2']")).isDisplayed();

		String coveragePageHeading_a = driver
				.findElement(By.xpath("//div[@class='text-xl font-medium text-neutral-100 mb-8']")).getText();

		String coveragePageHeading_e = "Select a coverage amount";

		Assert.assertEquals(coveragePageHeading_a, coveragePageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessagecoverage_e = "Select a coverage amount";

		String validationMessagecoverage_a = driver
				.findElement(By.xpath("//span[normalize-space()='Select a coverage amount']")).getText();

		Assert.assertEquals(validationMessagecoverage_a, validationMessagecoverage_e);

	}

	// method to check coverage page submission
	@Test(dependsOnMethods = { "coveragePageElementsCheck" }, priority = 17)
	public static void coveragePageSubmission() throws InterruptedException {

		WebElement slider = driver.findElement(By.xpath("//div[@class='px-2']"));

		// Using Action Class
		Actions move = new Actions(driver);
		move.moveToElement(slider).clickAndHold().moveByOffset(1, 10).release().perform();

		// slider working
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait date_page = new WebDriverWait(driver, 30);
		date_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='date']")));

		Thread.sleep(2000);

	}

	// method to check datePage Elements and validations
	@Test(dependsOnMethods = { "emailPageSubmission" }, priority = 18)
	public static void datePageElementsCheck() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='date']")).isDisplayed();

		String datePageHeading_a = driver
				.findElement(By.xpath("//div[@class='text-xl text-neutral-100 font-medium mb-8']")).getText();

		String datePageHeading_e = "Date of Birth";

		Assert.assertEquals(datePageHeading_a, datePageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessagedate_e = "Please fill this in";

		String validationMessagedate_a = driver
				.findElement(By.xpath("//span[@class='text-red-500 text-sm font-normal']")).getText();

		Assert.assertEquals(validationMessagedate_a, validationMessagedate_e);

	}

	// method to check date page submission
	@Test(dependsOnMethods = { "datePageElementsCheck" }, priority = 19)
	public static void datePageSubmission() throws InterruptedException {

		WebElement date = driver.findElement(By.xpath("//input[@id='date']"));

		date.sendKeys("11");
		date.sendKeys("18");
		date.sendKeys("1992");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait gender_page = new WebDriverWait(driver, 30);
		gender_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Male')]")));

		Thread.sleep(2000);

	}

	// method to check genderPage Elements and validations
	@Test(dependsOnMethods = { "emailPageSubmission" }, priority = 20)
	public static void genderPageElementsCheck() throws InterruptedException {

		driver.findElement(By.xpath("//div[contains(@class,'truncate !text-xl font-medium')]")).isDisplayed();

		String genderPageHeading_a = driver
				.findElement(By.xpath("//div[contains(@class,'truncate !text-xl font-medium')]")).getText();

		String genderPageHeading_e = "Gender";

		Assert.assertEquals(genderPageHeading_a, genderPageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessagegender_e = "Please make a selection";

		String validationMessagegender_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']")).getText();

		Assert.assertEquals(validationMessagegender_a, validationMessagegender_e);

	}

	// method to check gender page submission
	@Test(dependsOnMethods = { "genderPageElementsCheck" }, priority = 21)
	public static void genderPageSubmission() throws InterruptedException {

		WebElement gender_m = driver.findElement(By.xpath("//input[@value='0']"));

		gender_m.click();

		Thread.sleep(2000);

		WebDriverWait phone_page = new WebDriverWait(driver, 30);
		phone_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='answer']")));

		Thread.sleep(2000);

	}

	// method to check phone Page Elements and validations
	@Test(dependsOnMethods = { "genderPageSubmission" }, priority = 22)
	public static void phonePageElementsCheck() throws InterruptedException {

		String phonePageHeading_a = driver
				.findElement(By.xpath("//div[@class='text-xl text-neutral-100 font-medium mb-8']")).getText();

		String phonePageHeading_e = "Phone Number";

		Assert.assertEquals(phonePageHeading_a, phonePageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessagePhone_e = "Please fill this in";

		String validationMessagePhone_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please fill this in']")).getText();

		Assert.assertEquals(validationMessagePhone_a, validationMessagePhone_e);

		WebElement phonefield = driver.findElement(By.xpath("//input[@name='answer']"));

		phonefield.sendKeys("80");

		String validationMessagePhonewrong_e = "Please use a valid number";

		String validationMessagePhonewrong_a = driver
				.findElement(By.xpath("// span[normalize-space()='Please use a valid number']")).getText();

		Assert.assertEquals(validationMessagePhonewrong_a, validationMessagePhonewrong_e);

	}

	// method to check phone Page submission
	@Test(dependsOnMethods = { "phonePageElementsCheck" }, priority = 23)
	public static void phonePageSubmission() throws InterruptedException {

		Thread.sleep(2000);

		WebElement phonefield = driver.findElement(By.xpath("//input[@name='answer']"));

		phonefield.clear();

		phonefield.sendKeys("8054186612");

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait address_page = new WebDriverWait(driver, 30);
		address_page.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[contains(@name,'isAuthReleaseAgree')]")));

		Thread.sleep(2000);

	}

	// method to check address Page Elements and validations
	@Test(dependsOnMethods = { "phonePageSubmission" }, priority = 24)
	public static void addressPageElementsCheck() throws InterruptedException {

		String addressPageHeading_a = driver
				.findElement(By.xpath("//div[@class='text-xl text-neutral-100 font-medium mb-8']")).getText();

		String addressPageHeading_e = "Address";

		Assert.assertEquals(addressPageHeading_a, addressPageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessagePhoneaddress_e = "Please fill this in";
		String validationMessagePhoneconsent_e = "Please check the box to continue";
		String validationMessagePhoneHippaNotice_e = "Please check the box to continue";

		String validationMessagePhoneaddress_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please fill this in']")).getText();

		String validationMessagePhoneconsent_a = driver.findElement(By.xpath(
				"//div[@class='text-red-500 text-sm font-normal mb-2']//span[contains(text(),'Please check the box to continue')]"))
				.getText();

		String validationMessagePhoneHippaNotice_a = driver.findElement(By.xpath(
				"//body/div[@id='__next']/div/div[@class='h-screen flex flex-col bg-white']/div[@class='flex-grow min-h-0 overflow-y-auto']/div[@class='pt-2 pb-24']/div[@class='max-w-lg mt-2 lg:mt-16 mx-auto pt-2']/div/div[@class='p-4']/form/div[@class='text-sm text-neutral-100']/div[@class='text-sm mt-12']/div[1]/span[1]"))
				.getText();

		Assert.assertEquals(validationMessagePhoneaddress_a, validationMessagePhoneaddress_e);
		Assert.assertEquals(validationMessagePhoneconsent_a, validationMessagePhoneconsent_e);
		Assert.assertEquals(validationMessagePhoneHippaNotice_a, validationMessagePhoneHippaNotice_e);

		Thread.sleep(2000);

	}

	// method to check address page field data filling
	@Test(dependsOnMethods = { "addressPageElementsCheck" }, priority = 25)
	public static void addressPageFilldata() throws InterruptedException {

		Thread.sleep(2000);

		WebElement phonefield = driver
				.findElement(By.xpath("//div[@class='underline cursor-pointer text-primary-main']"));

		phonefield.click();

		driver.findElement(By.xpath("//div[normalize-space()='Street']")).isDisplayed();

		Thread.sleep(1000);

		WebElement street_e = driver.findElement(By.xpath("//input[@name='address.street']"));

		WebElement city_e = driver.findElement(By.xpath("//input[@name='address.city']"));

		WebElement zipcode_e = driver.findElement(By.xpath("//input[@name='address.zipCode']"));

		WebElement state_e = driver.findElement(By.xpath("//select[@id='state']"));

		Select ddState_e = new Select(state_e);

		ddState_e.selectByVisibleText("Indiana");

		street_e.sendKeys("test Street 99");
		city_e.sendKeys("test city");
		zipcode_e.sendKeys("11223");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[contains(@name,'isAuthReleaseAgree')]")).click();

		driver.findElement(By.xpath("//input[contains(@name,'isConsentBusiness')]")).click();

		Thread.sleep(2000);

	}

	// method to check address page submission
	@Test(dependsOnMethods = { "addressPageFilldata" }, priority = 26)
	public static void addressPageSubmission() throws InterruptedException {

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);

		WebDriverWait hw_page = new WebDriverWait(driver, 30);
		hw_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='height']")));

		Thread.sleep(2000);

	}

	// method to check weight and height Page Elements and validations
	@Test(dependsOnMethods = { "addressPageSubmission" }, priority = 27)
	public static void weightHeightPageElementsCheck() throws InterruptedException {

		String whPageHeading_a = driver
				.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div/div/div/form/div[1]")).getText();

		String whPageHeading_e = "What is your height and weight?";

		Assert.assertEquals(whPageHeading_a, whPageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessageHeight_e = "Please select one";
		String validationMessageWeight_e = "Please fill this in";

		String validationMessageHeight_a = driver.findElement(By.xpath("//span[normalize-space()='Please select one']"))
				.getText();

		String validationMessageWeight_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please fill this in']")).getText();

		Assert.assertEquals(validationMessageHeight_a, validationMessageHeight_e);
		Assert.assertEquals(validationMessageWeight_a, validationMessageWeight_e);

		Thread.sleep(1000);

		WebElement height_e = driver.findElement(By.xpath("//select[@id='height']"));

		Select ddheight_e = new Select(height_e);

		ddheight_e.selectByVisibleText("5'8\"");

		driver.findElement(By.xpath("//input[@name='weight']")).sendKeys("198");

		Thread.sleep(3000);

	}

	// method to check height weight page submission
	@Test(dependsOnMethods = { "weightHeightPageElementsCheck" }, priority = 28)
	public static void weightHeightPageSubmission() throws InterruptedException {

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait question_page = new WebDriverWait(driver, 30);
		question_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='No']")));

		Thread.sleep(3000);

	}

	// method to check question Page Elements and validations
	@Test(dependsOnMethods = { "weightHeightPageSubmission" }, priority = 29)
	public static void questionPageElementsCheck() throws InterruptedException {

		String questionPageHeading_a = driver.findElement(By.xpath("//div[@class='text-neutral-100 mb-8']")).getText();

		String questionPageHeading_e = "Are you currently taking any medication?";

		Assert.assertEquals(questionPageHeading_a, questionPageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessageQuestion_e = "Please make a selection";

		String validationMessageQuestion_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']")).getText();

		Assert.assertEquals(validationMessageQuestion_a, validationMessageQuestion_e);

		Thread.sleep(1000);

	}

	// method to check question page submission
	@Test(dependsOnMethods = { "weightHeightPageSubmission" }, priority = 30)
	public static void questionPageSubmission() throws InterruptedException {

		driver.findElement(By.xpath("//label[normalize-space()='No']")).click();

		WebDriverWait question2_page = new WebDriverWait(driver, 30);
		question2_page.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[normalize-space()='None of the above']")));

		Thread.sleep(3000);

	}

	// method to check question Page 2 Elements and validations
	@Test(dependsOnMethods = { "questionPageSubmission" }, priority = 31)
	public static void questionPage2ElementsCheck() throws InterruptedException {

		String questionPage2Heading_a = driver.findElement(By.xpath("//div[@class='text-neutral-100 mb-8']")).getText();

		String questionPage2Heading_e = "In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member of the medical profession as having any of the following:";

		Assert.assertEquals(questionPage2Heading_a, questionPage2Heading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessageQuestion2_e = "Please make a selection";

		String validationMessageQuestion2_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']")).getText();

		Assert.assertEquals(validationMessageQuestion2_a, validationMessageQuestion2_e);

		Thread.sleep(1000);

		driver.findElement(By.xpath("//label[normalize-space()='None of the above']")).click();

		Thread.sleep(1000);

	}

	// method to check question page 2 submission
	@Test(dependsOnMethods = { "questionPage2ElementsCheck" }, priority = 32)
	public static void questionPage2Submission() throws InterruptedException {

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait question2_page = new WebDriverWait(driver, 30);
		question2_page.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Kidney Disorder']")));

		Thread.sleep(3000);

	}

	// method to check question Page 3 Elements and validations
	@Test(dependsOnMethods = { "questionPage2Submission" }, priority = 33)
	public static void questionPage3ElementsCheck() throws InterruptedException {

		String questionPage3Heading_a = driver.findElement(By.xpath("//div[@class='text-neutral-100 mb-8']")).getText();

		String questionPage3Heading_e = "In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member of the medical profession as having any of the following:";

		Assert.assertEquals(questionPage3Heading_a, questionPage3Heading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessageQuestion3_e = "Please make a selection";

		String validationMessageQuestion3_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']")).getText();

		Assert.assertEquals(validationMessageQuestion3_a, validationMessageQuestion3_e);

		Thread.sleep(1000);

		driver.findElement(By.xpath("//label[normalize-space()='None of the above']")).click();

		Thread.sleep(1000);

	}

	// method to check question page 3 submission
	@Test(dependsOnMethods = { "questionPage3ElementsCheck" }, priority = 34)
	public static void questionPage3Submission() throws InterruptedException {

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait health_page = new WebDriverWait(driver, 30);
		health_page.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='No']")));

		Thread.sleep(3000);

	}

	// method to check healthcare Page Elements and validations
	@Test(dependsOnMethods = { "questionPage3Submission" }, priority = 35)
	public static void healthcarePageElementsCheck() throws InterruptedException {

		String healthcarePageHeading_a = driver.findElement(By.xpath("//div[@class='text-neutral-100 mb-8']"))
				.getText();

		String healthcarePageHeading_e = "Have you consulted, been advised or been examined by any healthcare provider for any other medical reason within the last ten years, or as indicated above?";

		Assert.assertEquals(healthcarePageHeading_a, healthcarePageHeading_e);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);

		String validationMessagehealth_e = "Please make a selection";

		String validationMessagehealth_a = driver
				.findElement(By.xpath("//span[normalize-space()='Please make a selection']")).getText();

		Assert.assertEquals(validationMessagehealth_a, validationMessagehealth_e);

		Thread.sleep(1000);

	}

	// method to check health care page submission
	@Test(dependsOnMethods = { "healthcarePageElementsCheck" }, priority = 36)
	public static void healthcarePage3Submission() throws InterruptedException {

		driver.findElement(By.xpath("//label[normalize-space()='No']")).click();

		WebDriverWait question2_page = new WebDriverWait(driver, 30);
		question2_page.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Applicant Type']")));

		Thread.sleep(3000);

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
