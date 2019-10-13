package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.HomePage;
import pages.InputFormsPage;

public class Inputform extends BaseTest {
	boolean flg = false;

	@Test(priority = 0)
	public void TC_001_invalidLoginTest_InvalidUserNameInvalidPassword() {

		// *************PAGE INSTANTIATIONS*************
		InputFormsPage homePage = new InputFormsPage(driver);

		// *************PAGE METHODS********************

		Childtest = test.createNode("TC_001 Simple for TC for Single input");
		if (homePage.verfySimpleForm() == true) {
			Assert.assertTrue(true);
			Childtest.log(Status.PASS,MarkupHelper.createLabel("Smpleform with single input PASSED", ExtentColor.BLUE));
		} else {
			Assert.assertFalse(true);
			Childtest.log(Status.FAIL, MarkupHelper.createLabel("Smpleform with single input FAILED", ExtentColor.RED));
		}

		boolean blnFlagJHeader = homePage.verfyHeader1();
		if (blnFlagJHeader) {
			Childtest.log(Status.PASS, MarkupHelper.createLabel("Verify Header PASSED", ExtentColor.BLUE));
			Assert.assertTrue(blnFlagJHeader);
			
		} else {
			
			Childtest.log(Status.FAIL, MarkupHelper.createLabel("Verify Header FAILED", ExtentColor.RED));
			Assert.assertTrue(blnFlagJHeader);
			
		}
	}

}
