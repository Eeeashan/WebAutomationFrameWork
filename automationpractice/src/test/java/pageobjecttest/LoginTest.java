package pageobjecttest;

import base.BrowserDriver;
import datareader.Data;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.Login;

import java.io.IOException;

public class LoginTest extends BrowserDriver {
    Login loginTest = new Login();

    @BeforeMethod
    public void initializeElement(){
        loginTest = PageFactory.initElements(driver,Login.class);
    }
    @DataProvider
    public Object[][] getLoginData() throws IOException, InvalidFormatException {
        Object data [] [] = Data.getTestData("Sheet3");
        return data;
    }

    @Test(dataProvider = "getLoginData")
    public void clickSignIn(String email, String password){
        loginTest.clickSignIn();
        loginTest.email(email);
        loginTest.pas(password);
        loginTest.clickCont();

    }
}
