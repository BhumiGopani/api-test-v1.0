package Main;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TcExecute {

	String TCFilepath = "C:\\Users\\Bhumi Gobani\\eclipse-workspace\\api-finreach-v1.0\\src\\TestCaseSheet\\TestCaseSheetV1.0.xlsx";
	String TCTagname = "sample";

	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.out.println("TestCase sheet name is " + TCTagname);
	}

	@Test
	public void test() throws Exception {
		TCExecuteLogic.execute(TCFilepath, TCTagname);
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("We have added test results in Test Case sheet");
	}

//	 can be used for debugging
//	 public static void main (String[] args) throws IOException, ParseException {
//	 String TCFilepath =
//	 "C:\\Users\\Bhumi Gobani\\eclipse-workspace\\api-finreach-v1.0\\src\\TestCaseSheet\\TestCaseSheetV1.0.xlsx";
//	 String TCTagname = "sample";
//	 System.out.println("TestCase sheet name is " + TCTagname);
//	 tCExecuteLogic.execute(TCFilepath,TCTagname);
//	 System.out.println("We have added test results in Test Case sheet");
//	 }
}
