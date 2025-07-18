package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import factory.BrowserFactory;
import utilities.WebDriverManager;

import java.io.File;
import java.time.Duration;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setupBrowser() {
        String browser = System.getProperty("browser", "chrome");
        WebDriver driver = BrowserFactory.getBrowser(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverManager.setDriver(driver);
        logger.info("Browser launched and WebDriver initialized.");

        File file = new File("data.xlsx");
    }

    @After
    public void tearDownBrowser() {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
    }
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {

            TakesScreenshot ts=(TakesScreenshot) WebDriverManager.getDriver();
            byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png",scenario.getName());

        }

    }
}