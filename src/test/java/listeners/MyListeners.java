package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class MyListeners implements ITestListener{
		
	public static ExtentTest extenttest;
	public static ExtentReports extent;
	
	public static ThreadLocal<ExtentTest> tests=new ThreadLocal<>();
	
		public static ExtentTest getTest()
		{
			return tests.get();
		}
		
		@Override
		public void onTestStart(ITestResult result)
		{
			String TestName=result.getName();
			System.out.println(TestName + " has started.");
			extenttest=BaseTest.extent.createTest(result.getMethod().getMethodName());
			tests.set(extenttest);
			System.out.println();
		}
		
		@Override
		public void onTestSuccess(ITestResult result)
		{
			String TestName=result.getName();
			System.out.println(TestName + " is successfully  executed.");
			tests.get().pass("Test passed");
			System.out.println();
		}
		
		@Override
		public void onTestFailure(ITestResult result)
		{
			String TestName=result.getName();
			System.out.println(TestName + " has failed.");
			tests.get().fail("Test failed");
			System.out.println();
		}
		
		@Override
		public void onTestSkipped(ITestResult result)
		{
			String TestName=result.getName();
			System.out.println(TestName + " got skipped.");
			tests.get().skip("Test skipped");
			System.out.println();
		}
		
		
	

}
