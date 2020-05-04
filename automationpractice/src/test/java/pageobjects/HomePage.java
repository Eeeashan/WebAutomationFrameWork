package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    /*
    * All the web elements will be available in this class
    * Assosiet methods
    * */
    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]/a")
    WebElement womenTab;

    @FindBy(xpath = "//*[@id=\"search_query_top\"]")
    WebElement searchBox;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[1]/div/a[1]/img")
    WebElement printedSummerDress;

    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
    WebElement addToCart;

    public void sendKeys() throws InterruptedException {
        searchBox.sendKeys("dress", Keys.ENTER);
        printedSummerDress.click();
        addToCart.click();
        Thread.sleep(2000);

    }
    public void clickWomenTab(){
        womenTab.click();
    }

}
