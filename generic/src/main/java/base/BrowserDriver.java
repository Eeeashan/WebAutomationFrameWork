package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
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
    /**
     * Browserstack Credentials
     *
     * */
    public static final String browserstack_username = System.getProperty("bsUName", "raiathasaneashan1");
    public static final String browserstack_automateKey = System.getProperty("bsSecretKey", "wMcqz6hxJyPm9wpxoFsK");
    /**
     *
     * Saucelab Credentials
     *
     * */
    public static final String saucelabs_username = null;
    public static final String saucelabs_accesskey = null;


    @Parameters({"useCloudEnv","cloudEnvName", "os","os_version", "browserName", "browserVersion", "url"})

    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("xyz") String cloudEnvName,
                      @Optional("mac") String os, @Optional("10") String os_version, @Optional("firefox") String browserName, @Optional("34")
                              String browserVersion, @Optional("https://www.google.com") String url) throws IOException {


        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_automateKey, os, os_version, browserName, browserVersion);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
            }
        } else {
            getLocalDriver(browserName, os);
        }

        //getLocalDriver(browserName, os);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // 20
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS); //35
        //driver.manage().window().maximize();
        driver.get(url);

    }
    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os, String os_version, String browserName,
                                    String browserVersion) throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", browserName);
        caps.setCapability("browser_version", browserVersion);
        caps.setCapability("os", os);
        caps.setCapability("os_version", os_version);

        if (envName.equalsIgnoreCase("Saucelabs")){
            driver = new RemoteWebDriver(new URL("https://"+envUsername+":"+envAccessKey+"@ondemand.saucelabs.com:80/wd/hub"),caps);
        }else if (envName.equalsIgnoreCase("browserstack")){
            driver = new RemoteWebDriver(new URL("https://" + envUsername + ":" + envAccessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

        }
    return driver;

    }

    public WebDriver getLocalDriver(String browserName, String os) {

        // if (browserName == "Chrome")
        if (browserName.equalsIgnoreCase("chrome")) {

            /**
             * Command Line Arguments
             * https://peter.sh/experiments/chromium-command-line-switches/
             * */

            ChromeOptions options =new ChromeOptions();
            // options.setHeadless(true);
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--incognito");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            if (os.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\easha\\Downloads\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver(options);
                //  TestLogger.log("Chrome Browser Launched");
            } else if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "../generic/drivers/mac/chromedriver");
                driver = new ChromeDriver(options);
                // TestLogger.log("Chrome Browser Launched");
            }
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            /**
             * https://chercher.tech/java/chrome-firefox-options-selenium-webdriver
             *
             * */
            FirefoxOptions options = new FirefoxOptions();
            //options.setHeadless(true);
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--private");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);


            if (os.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.gecko.driver", "../generic/drivers/windows/geckodriver.exe");
                driver = new FirefoxDriver(options);
            } else if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.gecko.driver", "../generic/drivers/mac/geckodriver");
                driver = new FirefoxDriver(options);
            }
        }

        return driver;
    }


    @AfterMethod
    public void closeBrowser(){

        driver.quit();
    }

}
