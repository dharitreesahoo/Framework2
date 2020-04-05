package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.HomePage;
import pages.InputFormsPage;

public class RadioButtonDemo extends BaseTest{
	 @Test (priority = 0)
	    public void TC_001_invalidLoginTest_InvalidUserNameInvalidPassword () {
	 
	        //*************PAGE INSTANTIATIONS*************
	        InputFormsPage homePage = new InputFormsPage(driver);
	 
	        //*************PAGE METHODS********************
	       // Parenttest = extent.createTest("TC001 Simple form demo");
	        homePage.verfySimpleForm();
			//sddrd comment
	      	    }
	 
	    @Test (priority = 1)
	    public void TC_002_invalidLoginTest_EmptyUserEmptyPassword () {
	        //*************PAGE INSTANTIATIONS*************
	    	InputFormsPage homePage = new InputFormsPage(driver);
	    	homePage.verfySimpleForm();
	    	Childtest = test.createNode("TC_002 invalidLoginTest_InvalidUserNameInvalidPassword Started************");
			//Childtest.log(Status.PASS, MarkupHelper.createLabel("User is able to Check check box", ExtentColor.BLUE));
	    	Childtest.log(Status.PASS, MarkupHelper.createLabel("Test level execution", ExtentColor.BLUE));
	        //*************PAGE METHODS********************
	    } 
	    @Test (priority = 1)
	    public void TC_003_invalidLoginTest_EmptyUserEmptyPassword () {
	        //*************PAGE INSTANTIATIONS*************
	    	 InputFormsPage homePage = new InputFormsPage(driver);
	    	Childtest = test.createNode("TC_002 invalidLoginTest_InvalidUserNameInvalidPassword Started************");
	    	homePage.verfySimpleForm();
	    	Assert.assertEquals("abc", "abc1");
	    	//Childtest.log(Status.PASS, MarkupHelper.createLabel("TC failed", ExtentColor.BLUE));
	        //*************PAGE METHODS********************
	    }    	


}
