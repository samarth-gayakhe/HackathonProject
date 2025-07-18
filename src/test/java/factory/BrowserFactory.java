package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class BrowserFactory {

    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);

    public static WebDriver getBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("Launched Chrome browser.");
                break;
            case "edge":
//                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                logger.info("Launched Edge browser.");
                break;
            default:
                logger.error("Invalid browser name: " + browserName + ". Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }
}