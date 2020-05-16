package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {


    @FindBy(xpath = "//a[@_sp='m570.l1524']")
    WebElement clickSignIn;
    @FindBy(xpath = "//input[@id='userid']")
    WebElement emailInput;
    @FindBy(xpath = "//button[@id='signin-continue-btn']")
    WebElement clickContinue;
    @FindBy(xpath = "//input[@id='pass']")
    WebElement pass;
    @FindBy(xpath = "//button[@id='sgnBt']")
    WebElement signInButton;

    public void clickSignIn(
    ){
        clickSignIn.click();

    }
    public void email(String email){
        emailInput.sendKeys(email);
    }
    public void pas(String password){
        pass.sendKeys(password);
    }
    public void clickCont(){
        signInButton.click();
    }
    public void click(){
        clickContinue.click();
    }


}
