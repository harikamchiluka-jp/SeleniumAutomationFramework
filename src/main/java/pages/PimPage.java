package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.CommonMethods;
import utils.WaitUtils;

public class PimPage {

	public WebDriver driver; 
	  public Properties props;
	  public WaitUtils wt=new WaitUtils(driver);
	  public CommonMethods cm=new CommonMethods();
	  public static String Cap_Emp_Id1;
	
	public PimPage(WebDriver driver, Properties props) {
		this.driver = driver;
		//this.props = props;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//a[@href='/web/index.php/pim/viewPimModule']")
	public WebElement Pim;
	
	@FindBy(xpath="//a[contains(text(),'Add Employee')]")
	public WebElement AddEmp;
	
	@FindBy(xpath="//a[contains(text(),'Employee List')]")
	public WebElement EmpList;
	
	@FindBy(xpath="//input[@name='firstName']")
	public WebElement FirstName1;//first name while creating
	
	@FindBy(xpath="//input[@name='middleName']")
	public WebElement MiddleName1;//middle name while creating
	
	@FindBy(xpath="//input[@name='lastName']")
	public WebElement LastName1;//last name while creating
	
	@FindBy(xpath="//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/child::input")
	public WebElement EmpId1;//auto generated emp id while creating
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement SaveButton;
	
	@FindBy(xpath="//label[text()='Employee Id']/parent::div/following-sibling::div//input")
	public WebElement SearchEmpID;

	@FindBy(xpath="//button[@type='submit']")
	public WebElement SearchButton;
	
	@FindBy(xpath="//div[@role='cell'][2]")
	public WebElement PostSearchEmpId;
	
	@FindBy(xpath="//div[@role='cell'][3]")
	public WebElement PostSearchEmpName;
	
	@FindBy(xpath="//div[@role='cell'][4]")
	public WebElement PostSearchLastName;
	
	@FindBy(xpath="//img[@alt='profile picture']")
	public WebElement profile;
	
	@FindBy(xpath="//a[@href='/web/index.php/auth/logout']")
	public WebElement logout;
	
	@FindBy(xpath="//div[@class='oxd-table-body']//div[@role='row']")
	public WebElement tablerow;
	
	

	public void addEmployeeData(String fn1, String mn1, String ln1)
	{
		
		/*
		 * Actions a = new Actions(driver);
		 * a.moveToElement(Pim).click().build().perform();
		 */
		
		wt.waitFluent(AddEmp);
		AddEmp.click();
		wt.waitFor(10);
		
		wt.waitFluent(FirstName1);
		FirstName1.sendKeys(fn1);
		wt.waitFor(10);
		MiddleName1.sendKeys(mn1);
		wt.waitFor(10);
		LastName1.sendKeys(ln1);
		wt.waitFor(10);
		wt.waitFor(10,EmpId1);
		Cap_Emp_Id1=EmpId1.getDomProperty("value");//capturing the auto generated emp id 
		System.out.println("The captured emp id is " + Cap_Emp_Id1);
		SaveButton.click();
		wt.waitFor(20);
		
		wt.waitForPageToLoad(driver);
		
		
		
				
	}
	
	public void empSearch()
	{
		wt.waitFluent(EmpList);
		System.out.println("PIM menu option clicked");
		
		//EmpList.click();
		wt.waitFor(20);
		cm.ScrollIntoViewTop(SearchEmpID, driver);
		wt.waitFluent(SearchEmpID);
		System.out.println("The captured emp id is " + Cap_Emp_Id1);
		SearchEmpID.clear();
		SearchEmpID.sendKeys(Cap_Emp_Id1);
		
		
		wt.waitFor(20);
		wt.waitFluent(SearchButton);
		SearchButton.click();
		wt.waitFor(30);
		//cm.ScrollIntoViewBottom(driver);
		wt.waitForPageToLoad(driver);
			
	}
	
}
