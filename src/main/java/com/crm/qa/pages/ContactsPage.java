

package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactslabel;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement companyName;
	
	@FindBy(xpath="//input[@type='submit'and @value='Save']")
	WebElement savebtn;
	
	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel(){
		return contactslabel.isDisplayed();
	}
	
	public void selectContactsByNumber(String num){
		driver.findElement(By.xpath("//table[@class='datacard']/tbody/tr[5]/td["+num+"]")).click();
		
	}
	
	public void createNewContact(String title, String ftName, String ltName, String compName){
		
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		companyName.sendKeys(compName);
		savebtn.click();
		
	}
}
