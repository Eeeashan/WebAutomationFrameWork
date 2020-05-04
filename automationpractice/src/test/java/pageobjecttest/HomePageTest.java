package pageobjecttest;

import base.BrowserDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class HomePageTest extends BrowserDriver {

    /*HomePage test classes will be here.
    *
    * */
    HomePage homePage = null;
    @BeforeMethod
    public void initializeElements(){
        homePage = PageFactory.initElements(driver,HomePage.class);
    }
    @Test
    public void clickWomenTab(){
        homePage.clickWomenTab();
    }
    @Test
    public void sendKeys() throws InterruptedException {
        homePage.sendKeys();

    }

}
