package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class InputFormsPage extends BasePage {
	WebDriver driver;

	//WebElement lnkInputorms = this.getLocator(By.xpath("//a[text()='Input Forms']"));
	
	public InputFormsPage(WebDriver driver) {
		//this.driver= driver;
		super(driver);
	}
	
	 By lnkInputForms = By.xpath("//a[text()='Input Forms']");
	 By txtMsgBox = By.xpath("//input[@id='user-message']");
	 By btnShowMsg = By.xpath("//button[text()='Show Message']");
	 By labelMsg = By.xpath("//label[text()='Your Message: ']/following-sibling::span");
	 By lnkSimpleFormDemo =  By.xpath("(//a[text()='Simple Form Demo'])[2]");
	 By lnkCheckBoxDemo =  By.xpath("(//a[text()='Checkbox Demo'])[2]");
	 By chkBox =  By.xpath("//input[@id='isAgeSelected']");
	 By chkBoxSuccessMsg =  By.xpath("//input[@id='isAgeSelected']");
			
/*	@FindBy(xpath = "//a[text()='Input Forms']")
	WebElement lnkInputForms;*/
	
/*	@FindBy(xpath = "(//a[text()='Simple Form Demo'])[2]")
	WebElement lnkSimpleFormDemo;*/
	/*
	@FindBy(xpath="//input[@id='user-message']")
	WebElement txtMsgBox;*/
	
/*	@FindBy(xpath="//button[text()='Show Message']")
	WebElement btnShowMsg;*/
	
	/*@FindBy(xpath="//label[text()='Your Message: ']/following-sibling::span")
	WebElement labelMsg;*/
	/*
	@FindBy(xpath = "(//a[text()='Checkbox Demo'])[2]")
	WebElement lnkCheckBoxDemo;*/
	
	/*@FindBy(xpath = "//input[@id='isAgeSelected']")
	WebElement chkBox;*/
	
	/*
	@FindBy(xpath = "//input[@id='isAgeSelected']")
	WebElement chkBoxSuccessMsg;
	*/

	public boolean verfySimpleForm() {
		boolean  blnflg = false;
		
		this.click(lnkInputForms);
		this.writeText(txtMsgBox, "omNamahSivaya");
		this.click(btnShowMsg);
		String expctedText = this.readText(labelMsg);
		if(expctedText.equals("omNamahSivaya"))
		{
			blnflg = true;
		}
		
		return blnflg;
	}
	public boolean verfyHeader()
	{
		boolean  blnflgHedaer = false;
		String expectedText = this.getText(By.xpath("//div[text()='Single Input Field']"));
		if(expectedText.equals("Single Input Field"))
		{
			blnflgHedaer = true;
		}
		
		return blnflgHedaer;
	}
	
	public boolean verfyHeader1()
	{
		boolean  blnflgHedaer = false;
		String expectedText = this.getText(By.xpath("//div[text()='Single Input Field']"));
		if(expectedText.equals("Single Input Field1"))
		{
			blnflgHedaer = true;
		}
		
		return blnflgHedaer;
	}
	
	public void twoInputFields()
	{
		this.writeText(By.xpath(""), "omNamahSivaya");
		this.writeText(txtMsgBox, "omNamahSivaya");
	}

}
