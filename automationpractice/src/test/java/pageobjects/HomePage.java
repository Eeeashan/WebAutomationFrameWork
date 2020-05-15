package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static base.BrowserDriver.driver;

public class HomePage {

    /*
    * All the web elements will be available in this class
    * Assosiet methods
    * */
    @FindBy(xpath = "//input[@placeholder='Search for anything']")
    WebElement homeSearchHeader;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement clickSearch;
    @FindBy(xpath = "//a[@_sp='m570.l1524']")
    WebElement hoverOver;


    public void search()  {
        homeSearchHeader.sendKeys("iphonex");
        clickSearch.click();

    }
    public void hoverOver(){
        Actions act = new Actions(driver);
        act.moveToElement(hoverOver).perform();
    }
}

