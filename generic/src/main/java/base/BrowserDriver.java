package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;


public class BrowserDriver {
    /*
    * Will contain all the drivers in this class!
    * Also all the common activities for TEST activities
    * @opening
    * @Getting browser
    * @closing browser etc
    */
    public static WebDriver driver = null;
    public static ChromeOptions options =null;
    @Parameters({"url","driverDirectory", "browserName", "os"})

    @BeforeMethod
    public WebDriver setUp(String url, String driverDirectory, String browserName, String os){

        if(os.equalsIgnoreCase("windows")) {

            options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("incognito");
            System.setProperty("webdriver.chrome.driver", driverDirectory);
            driver = new ChromeDriver(options);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        }
        else {
            System.out.println("From MAC");
        }

    return driver;
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}
