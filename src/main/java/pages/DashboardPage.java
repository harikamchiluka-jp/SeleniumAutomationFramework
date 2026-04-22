package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class DashboardPage {

	private WebDriver driver; 
	  public Properties props;
	  private WaitUtils wt;
	
	public DashboardPage(WebDriver driver, Properties props) {
		this.driver = driver;
		//this.props = props;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//input[@placeholder='Search']")
	public WebElement MenuSearch;
	
	@FindBy(xpath="//a[@href='/web/index.php/pim/viewPimModule']")
	public WebElement Pim;
	
	public void SearchPimMenu()
	{
		wt=new WaitUtils(driver);
		wt.waitFor(10);
		wt.waitFluent(MenuSearch);
		MenuSearch.click();
		wt.waitFor(10);
		MenuSearch.sendKeys("Pim");
		wt.waitFor(10);
		Actions a = new Actions(driver);
		a.moveToElement(Pim).click().build().perform();
		
		wt.waitFor(10);
		
		
		
	}
	
}
