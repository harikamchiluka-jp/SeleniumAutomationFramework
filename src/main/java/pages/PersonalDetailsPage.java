package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class PersonalDetailsPage {

	private WebDriver driver; 
	  public Properties props;
	  private WaitUtils wt;
	
	public PersonalDetailsPage(WebDriver driver, Properties props) {
		this.driver = driver;
		//this.props = props;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@name='firstName']")
	public WebElement FirstName2;//firstname after saving
	
	@FindBy(xpath="//input[@name='middleName']")
	public WebElement MiddleName2;//middle name after saving
	
	@FindBy(xpath="//input[@name='lastName']")
	public WebElement LastName2;//last name after saving
	
	@FindBy(xpath="//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/child::input")
	public WebElement EmpId2;//emp id after saving
	
	@FindBy(xpath="//div[@class='orangehrm-tabs-wrapper'][1]")
	public WebElement PersonalDetails;
	
}
