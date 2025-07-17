package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utilities.WebDriverManager;

public class CommonStepDefinitions {

    private static final Logger logger = LogManager.getLogger(CommonStepDefinitions.class);
    @Given("user has launched the browser and navigated to {string}")
    public void userHasLaunchedTheBrowserAndNavigatedTo(String url) {
        WebDriver driver = WebDriverManager.getDriver();
        driver.get(url);
        logger.info("Navigated to URL: {}", url);
    }

    @When("^user searches \"([^\"]*)\"$")
    public void userSearches(String searchTerm) {
        WebDriver driver = WebDriverManager.getDriver();
        driver.findElement(By.id("twotabsearchtextbox")).click();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(searchTerm).build().perform();
        driver.findElement(By.id("nav-search-submit-button")).click();
        logger.info("Searched for: " + searchTerm);
    }

    @Then("Close the browser")
    public void closeTheBrowser() {
        // The browser closing is handled by @After hook in Hooks.java
        // This step can be removed from feature files if not needed as a separate step
        logger.info("Close browser step executed. Browser is closed by @After hook.");
    }
}