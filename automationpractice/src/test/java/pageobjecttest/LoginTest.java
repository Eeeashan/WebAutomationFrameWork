package pageobjecttest;

import base.BrowserDriver;
import datareader.MyDataReader;
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
    public Object[][] readData() throws Exception {
        String sheetName = null;
        MyDataReader myDataReader = new MyDataReader();
        //String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";
        myDataReader.setExcelFile("C:\\Users\\easha\\OneDrive\\Desktop\\Automation\\WebAutomationFrameWork\\automationpractice\\testData\\Test.xlsx");
        String environment = System.getProperty("env", "QA");

        if(environment.equals("QA")){
            sheetName="Sheet1";}
        else if(environment.equals("PROD")){
            sheetName="Sheet3";
        }

        Object [][] data = myDataReader.getExcelSheetData(sheetName);
        return data;
    }

    @Test(dataProvider = "readData")
    public void clickSignIn(String email, String password) throws InterruptedException {
        System.out.println("this is email "+email + password);
        loginTest.clickSignIn();
        Thread.sleep(2000);
        loginTest.email(email);
        Thread.sleep(2000);
        loginTest.click();
        loginTest.pas(password);
        Thread.sleep(2000);
        loginTest.clickCont();


    }
}
