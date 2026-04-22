package utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	WebDriver driver;
	
	public WaitUtils(WebDriver driver)
	{
		this.driver=driver;	
	}
	
	public void waitFor(int time,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitFor(int time)
	{
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(time));
		//wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void waitForPageToLoad(WebDriver driver)
	{
				new WebDriverWait(driver,Duration.ofSeconds(60))
				.until(WebDriver->((JavascriptExecutor)WebDriver)
						.executeScript("return document.readyState")
						.equals("complete"));
					
	}
	
	public void waitFluent(WebElement element)
	{
		Wait<WebDriver> wait=new FluentWait<>(driver)
		.withTimeout(Duration.ofSeconds(30))
		.pollingEvery(Duration.ofSeconds(5))
		.ignoring(NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	

}
