package tests;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import pages.PimPage;
import utils.WaitUtils;
import listeners.MyListeners;

public class OrangeHrmLiveTests extends BaseTest{
		
		public PimPage pp;
		public PersonalDetailsPage pd;
		public WaitUtils wt;
		public DashboardPage dp;
		static String Cap_Emp_Id1;
       
	
	@BeforeMethod(alwaysRun=true)
	public void LoginTest() throws AWTException, InterruptedException
	{
		wt = new WaitUtils(driver);
		
			LoginPage lp;
			String uname=props.getProperty("Username");
			String pwd=props.getProperty("Pswd"); 
			lp = new LoginPage(driver, props);
			
			  if(driver!=null)
			  lp.login(uname,pwd);
			  wt.waitFor(5);
  
			 		 
	}

	
	  @Test(priority=1) 
	  public void VerifyLogin() {
		  if(MyListeners.getTest()!=null)
		  {
		  // Login Verification
				 Assert.assertTrue(driver.getPageSource().contains("Dashboard"));
				 		  
				 MyListeners.getTest().info("Login Successful. Dashboard is visible");
				 MyListeners.getTest().info("Login Completed" );
	  }
	  }
	 
	
		@Test(priority=2)
		public void AddEmpTest()
		{
			System.out.println("Adding EMployee started");
			wt = new WaitUtils(driver);
			pp = new PimPage(driver,props);
	        pd = new PersonalDetailsPage(driver, props);
	        dp = new DashboardPage(driver, props);
	        
	        String EmpFN=props.getProperty("FN");
	        String EmpMN=props.getProperty("MN");
	        String EmpLN=props.getProperty("LN");
	        
	        driver.navigate().refresh();
	        wt.waitForPageToLoad(driver);
	        wt.waitFor(10);
	        dp.SearchPimMenu();
			pp.addEmployeeData(EmpFN, EmpMN, EmpLN);
			
			wt.waitFluent(pd.PersonalDetails);
			
			if(MyListeners.getTest()!=null)
			  {
			//Verification of Personal Details Page
			 Assert.assertTrue(pd.PersonalDetails.isDisplayed());
			 MyListeners.getTest().info("Personal Details page is visible after adding employee");
				  
				  
			//Verification of First Name
			 Assert.assertTrue(pp.FirstName1.getText().equalsIgnoreCase(pd.FirstName2.getText()));
			 MyListeners.getTest().info("Employee first name is shown correctly in Personal Details page");
				  
				  
			//Verification of Middle Name
			 Assert.assertTrue(pp.MiddleName1.getText().equalsIgnoreCase(pd.MiddleName2.getText()));
			 MyListeners.getTest().info("Employee middle name is shown correctly in Personal Details page");
				  
				  
			//Verification of Last Name
			 Assert.assertTrue(pp.LastName1.getText().equalsIgnoreCase(pd.LastName2.getText()));
			 MyListeners.getTest().info("Employee last name is shown correctly in Personal Details page");
				
			 Cap_Emp_Id1=pp.EmpId1.getText();//capturing the auto generated emp id 
			 String Emp_Id2=pd.EmpId2.getText();//storing the emp id after saving
				
				  
			 //Verification of Employee Id
			  Assert.assertTrue(Cap_Emp_Id1.equalsIgnoreCase(Emp_Id2));//comparing the captured emp id and emp id after saving the emp data
			  MyListeners.getTest().info("Employee id after save matches with the captured emp id");
				 
			  
			  MyListeners.getTest().info("Adding Employee is successful");
			  MyListeners.getTest().info("Adding Employee completed!");
			  System.out.println("Adding employee completed");  
				
			  }
		}
		
		@Test(priority=3, dependsOnMethods="AddEmpTest")
		public void SearchEmployee()
		{
			
			wt = new WaitUtils(driver);
			pp = new PimPage(driver,props);
	        pd = new PersonalDetailsPage(driver, props);
	        dp = new DashboardPage(driver, props);
	        //String Emp_Id2=pd.EmpId2.getText();//storing the emp id after saving
	        
	        System.out.println("Search Employee Test started");
	        
			wt.waitFor(10);
			dp.SearchPimMenu();	
			wt.waitFor(10);
			
			
			
			if(MyListeners.getTest()!=null)
			  {
				
					/*
					 * List<WebElement> rows=driver.findElements(By.xpath(
					 * "//div[@class='oxd-table-body']//div[@role='row']"));
					 * System.out.println("No. of rows are " + rows.size());
					 */
					
					pp.empSearch();
					
					
					
					//Verify Emp Id
					wt.waitFluent(pp.PostSearchEmpId);
					Assert.assertTrue(pp.PostSearchEmpId.getAttribute("value").equalsIgnoreCase(pd.EmpId2.getAttribute("value")));
					MyListeners.getTest().info("The emp id after search matches with the emp id of the employee created");
					
					//Verify Emp Name
					String fn=pd.FirstName2.getText();
					String mn=pd.MiddleName2.getText();
					String ln=pd.LastName2.getText();
					String firstmiddlename;
					
					wt.waitFluent(pp.PostSearchEmpName);
					Assert.assertTrue(pp.PostSearchEmpName.getAttribute("value").equalsIgnoreCase(fn + mn));
					MyListeners.getTest().info("The emp name after search matches with the emp name of the employee created");
					
					
					//Verify Last name
					wt.waitFluent(pp.PostSearchLastName);
					Assert.assertTrue(pp.PostSearchLastName.getAttribute("value").equalsIgnoreCase(ln));
					MyListeners.getTest().info("The emp last name after search matches with the emp last name of the employee created");
					
					
					
					//Verify only one record is displayed.
					List<WebElement> rows=driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));
					System.out.println("No. of rows are " + rows.size());
					Assert.assertEquals(rows.size(),1,"Employee table should contain exactly one row");
					
					
					MyListeners.getTest().info("Only one record has found for the search successfully");
					
					MyListeners.getTest().info("Employee Search is successful");
					MyListeners.getTest().info("Employee Search is completed!");
					System.out.println("Search Employee completed");	
			  }
		}
	
	@AfterMethod()
	public void Logout()
	{

		
		  // Defensive checks 
	if(pp == null)
	{ pp = new PimPage(driver, props); }
		 
		        wt = new WaitUtils(driver);
		        wt.waitFluent(pp.profile);
		        wt.waitFor(10);
		        pp.profile.click();
		        wt.waitFor(10);
				wt.waitFluent(pp.logout);
				pp.logout.click();
				
	}
}
