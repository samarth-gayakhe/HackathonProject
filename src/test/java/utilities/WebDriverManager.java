package utilities;

import org.openqa.selenium.WebDriver;

// This class is a utility to manage the WebDriver instance
public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}