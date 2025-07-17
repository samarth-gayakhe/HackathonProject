package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AmazonHomePage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AmazonHomePage.class);

    private By searchTextBox = By.id("twotabsearchtextbox");
    private By searchSubmitButton = By.id("nav-search-submit-button");
    private By allHamburgerMenu = By.id("nav-hamburger-menu");

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchText(String product) {
        WebElement searchInput = driver.findElement(searchTextBox);
        searchInput.click();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(product).build().perform();
        logger.info("Entered search text: " + product);
    }

    public SearchResultsPage clickSearchButton() {
        driver.findElement(searchSubmitButton).click();
        logger.info("Clicked search submit button.");
        return new SearchResultsPage(driver);
    }

    public HamburgerMenuPage clickAllHamburgerMenu() {
        driver.findElement(allHamburgerMenu).click();
        logger.info("Clicked 'All' hamburger menu.");
        return new HamburgerMenuPage(driver);
    }
}