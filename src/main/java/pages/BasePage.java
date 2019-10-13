package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import helpers.AlertHelper;
import helpers.LoggerHelper;



public class BasePage {
	 public WebDriver driver;
	    public WebDriverWait wait;
	    private Logger log = LoggerHelper.getLogger(BasePage.class);
	 
	    public BasePage (WebDriver driver){
	        this.driver = driver;
	        wait = new WebDriverWait(driver,15);
	    }
	 
	    //Wait Wrapper Method
	    public void waitVisibility(By elementBy) {
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
	    }
	 
	    //Click Method
	    public void click (By elementBy) {
	        waitVisibility(elementBy);
	        driver.findElement(elementBy).click();
	        log.info("Clicked on element " + driver.findElement(elementBy).getText());
	    }
	 
	    //Write Text
	    public void writeText (By elementBy, String text) {
	        waitVisibility(elementBy);
	        driver.findElement(elementBy).sendKeys(text);
	        Assert.assertEquals(driver.findElement(elementBy).getAttribute("value"), text);
	        log.info("Entered " + text + "  in the textbox " + elementBy);
	    }
	 
	    //Read Text
	    public String readText (By elementBy) {
	        waitVisibility(elementBy);
	        return driver.findElement(elementBy).getText();
	    }
	 
	    //Assert
	    public void assertEquals (By elementBy, String expectedText) {
	        waitVisibility(elementBy);
	        Assert.assertEquals(readText(elementBy), expectedText);
	 
	    }
	    public void selectValueInDropdown(By elementBy, String value) {
			Select select = new Select(driver.findElement(elementBy));
			select.selectByValue(value);
			 Assert.assertEquals(select.getFirstSelectedOption().getText(), value);
			log.info("Selected  " + value + " from dropdn " + select);
			
		}

		// Alert
		public Alert getAlert() {
			log.info("alert test: " + driver.switchTo().alert().getText());
			return driver.switchTo().alert();
		}

		public void acceptAlert() {
			getAlert().accept();
			log.info("accept Alert is done...");
		}

		public void dismissAlert() {
			getAlert().dismiss();
			log.info("dismiss Alert is done...");
		}

		public String getAlertText() {
			String text = getAlert().getText();
			log.info("alert text: " + text);
			return text;
		}

		public boolean isAlertPresent() {
			try {
				driver.switchTo().alert();
				log.info("alert is present");
				return true;
			} catch (NoAlertPresentException e) {
				log.info(e.getCause());
				return false;
			}
		}

		public void acceptAlertIfPresent() {
			if (isAlertPresent()) {
				acceptAlert();
			} else {
				log.info("Alert is not present..");
			}
		}

		public void dismissAlertIfPresent() {
			if (isAlertPresent()) {
				dismissAlert();
			} else {
				log.info("Alert is not present..");
			}
		}

		public void acceptPrompt(String text) {
			if (isAlertPresent()) {
				Alert alert = getAlert();
				alert.sendKeys(text);
				alert.accept();
				log.info("alert text: " + text);
			}
		}

		public boolean isDisplayed(By elementBy) {
			try {
				 driver.findElement(elementBy).isDisplayed();
				log.info("element is Displayed.." +  driver.findElement(elementBy).getText());
				//TestBase.logExtentReport("element is Displayed.." + element.getText());
				return true;
			} catch (Exception e) {
				log.error("element is not Displayed..", e.getCause());
				//TestBase.logExtentReport("element is not Displayed.." + e.getMessage());
				return false;
			}
		}

		public boolean isNotDisplayed(By elementBy) {
			try {
				driver.findElement(elementBy).isDisplayed();
				log.info("element is present.." + driver.findElement(elementBy).getText());
				//TestBase.logExtentReport("element is present.." + element.getText());
				return false;
			} catch (Exception e) {
				log.error("element is not present..");
				return true;
			}
		}

		public String readValueFromElement(By elementBy) {
			if (null == driver.findElement(elementBy)) {
				log.info("WebElement is null..");
				return null;
			}
			boolean status = driver.findElement(elementBy).isDisplayed();
			if (status) {
				log.info("element text is .." + driver.findElement(elementBy).getText());
				return driver.findElement(elementBy).getText();
			} else {
				return null;
			}
		}

		public String getText(By elementBy) {
			if (null == driver.findElement(elementBy)) {
				log.info("WebElement is null..");
				return null;
			}
			boolean status = driver.findElement(elementBy).isDisplayed();
			if (status) {
				log.info("element text is .." + driver.findElement(elementBy).getText());
				return driver.findElement(elementBy).getText();
			} else {
				return null;
			}
		}

		public String getTitle() {
			log.info("page title is: " + driver.getTitle());
			return driver.getTitle();
		}

}
