package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class BaseTest {

	protected WebDriver driver;
	public static Properties props;
	public static ExtentReports extent;
	
	LoginPage lp;
	
	
	@BeforeSuite
	public void SetUpReport()
	{
		//extent = Reports.createObject();
		
		String reportPath=System.getProperty("user.dir")+"/report/ExtentReport.html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);  //defines format and file path of the report
		
		reporter.config().setReportName("Automation Results");
		
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Harika");
		
		System.out.println("Extent Report Initialised");
	}
	
	@BeforeTest
	public  void BrowserSetUp() throws Exception
	{
		props=new Properties();
		File propsFile= new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\ConfigReader.properties");   //File propsFile= new File(C:\\Users\\HACHILUK\\OneDrive - Capgemini\\Desktop\\NG_Dynamics\\ng_dynamics\\src\\main\\java\\com\\nget\\config\\.properties");
		
		try
		{
			FileInputStream fis=new FileInputStream(propsFile);
			props.load(fis);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		driver=initializeBrowser();
		
		if(driver==null)
		{
			throw new
			RuntimeException("initializeBrowser() returned null driver");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(props.getProperty("url"));
		
		driver.navigate().to(props.getProperty("urlTest"));
	
	}
	
	public WebDriver initializeBrowser()
	{
		String BrowserName=props.getProperty("browser");
		
		
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			Map<String, Object> prefs=new HashMap<>();
			prefs.put("profile.default_content_setting_values.clipboard",  1);
			prefs.put("profile.default_content_setting_values.notifications",  2);
			prefs.put("profile.default_content_setting_values.geolocation",  2);
			
			ChromeOptions options=new ChromeOptions();
			options.setExperimentalOption("prefs",prefs);
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			
			WebDriverManager.chromedriver().setup(); //System.setProperty(props.getProperty("chromeBrowser"), props.getProperty("driverLocation"));
			driver=new ChromeDriver(options);
		}
				else if(BrowserName.equalsIgnoreCase("edge"))
				{
					//EdgeOptions options=new EdgeOptions();
					driver=new EdgeDriver();
				}
				else if(BrowserName.equalsIgnoreCase("firefox"))
				{
					//ChromeOptions options=new ChromeOptions();
					driver=new FirefoxDriver();
				}
		
											
				return driver;
		
	
	}
	
	
	
	
	/*
	 * @AfterTest public WebDriver TearDown() { if(driver!=null) driver.quit();
	 * return driver; }
	 */
	 
	 
	 
	
	
	
	
	  @AfterSuite 
	  public void tearDownReport() 
	  { extent.flush();
	  System.out.println("Extent Report Flushed"); }
	 
	 

}
