//@author Rishabh

package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setup(){
		
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

	@Test(priority=1)
	public void verifyContactsPageLabelTest(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contact label missing");
	}
	
	@Test(priority=2)
	public void selectContestTest(){
		contactsPage.selectContactsByNumber("1");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3, dataProvider="getCRMTestData")
	public void validateCreateNewContactTest(String title, String ftName, String ltName, String compName){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, ftName, ltName, compName);
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}

}
