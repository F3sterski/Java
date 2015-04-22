package com.example.seleniumdemo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
    	File file = new File("/usr/bin/firefox");
    	System.setProperty("webdriver.firefox.bin",file.getAbsolutePath());
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		driver.get("http://www.codebros.pl:666");
		
		element = driver.findElement(By.xpath("//*[@id=\"session_login\"]"));
		assertNotNull(element);
	}
	
	@Test
	public void RegistrationPage() throws InterruptedException{
		driver.get("http://www.codebros.pl:666");
		driver.findElement(By.linkText("Zarejestruj się")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element =
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/form/input")));
		Random r = new Random();
		int number = r.nextInt(100000);
		String n = Integer.toString(number);
		driver.findElement(By.id("user_login")).sendKeys("test"+n);
		driver.findElement(By.id("user_password")).sendKeys("testtest"+n);
		driver.findElement(By.id("user_password_confirmation")).sendKeys("testtest"+n);
		driver.findElement(By.id("user_email")).sendKeys("test"+n+"@test.pl");
		
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("rejestracja.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

	@Test
	public void LoginPage() throws InterruptedException{
		driver.get("http://www.codebros.pl:666");
		driver.findElement(By.id("session_login")).sendKeys("admin");
		driver.findElement(By.id("session_password")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@id=\"navbar\"]/form/input")).submit();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement elmanage = wait.until(ExpectedConditions.elementToBeClickable(By.id("manage")));
		elmanage.click();
		driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul[2]/li[4]/div/ul/li[3]/a")).click();
		WebElement element =
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/p/a")));		

		element.click();
		
		WebElement elStworz =
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/form/input")));		
		
		driver.findElement(By.id("tournament_name")).sendKeys("test");
		driver.findElement(By.id("tournament_description")).sendKeys("testtest");
		driver.findElement(By.id("tournament_max_members")).sendKeys("4");
		driver.findElement(By.id("tournament_event_date")).sendKeys("2014/10/10");
		
		
		elStworz.click();
				
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("tree.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
	
	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
	
}
