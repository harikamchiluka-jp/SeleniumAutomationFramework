package pages;

import java.awt.AWTException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class LoginPage {

	private WebDriver driver; 
	  public Properties props;
	  private WaitUtils wt;
	
	public LoginPage(WebDriver driver, Properties props) {
		this.driver = driver;
		//this.props = props;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement UserName;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement Password;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement LoginButton;
	
	
	
	
	public void login(String un, String pwd) throws AWTException, InterruptedException
	{
		wt=new WaitUtils(driver);
		wt.waitForPageToLoad(driver);
		wt.waitFluent(UserName);
		UserName.sendKeys(un);
		wt.waitFor(5);
		Password.sendKeys(pwd);
		wt.waitFor(5);
		LoginButton.click();
		wt.waitForPageToLoad(driver);
			
		
	}

}
