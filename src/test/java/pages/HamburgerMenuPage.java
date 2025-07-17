package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.excelUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HamburgerMenuPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(HamburgerMenuPage.class);

    private By seeAllButton = By.className("hmenu-compressed-btn");
    private By homeKitchenPetsCategory = By.xpath("//div[text()='Home, Kitchen, Pets']");
    private By homeKitchenSubcategories = By.xpath("//section[@class='category-section' and normalize-space(@aria-labelledby)='Home & Kitchen']//ul[not(ancestor::section[@aria-labelledby='Pet Supplies'])]/li");
    private By closeMenuButton = By.id("hmenu-close-icon");

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickSeeAll() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(seeAllButton));
        driver.findElement(seeAllButton).click();
        logger.info("Clicked 'See All' button in hamburger menu.");
    }

    public void clickHomeKitchenPetsCategory() {
        driver.findElement(homeKitchenPetsCategory).click();
        logger.info("Clicked 'Home, Kitchen, Pets' category.");
    }

    public void storeHomeKitchenResultsAndCloseMenu() throws Exception {
        List<WebElement> hmenu = driver.findElements(homeKitchenSubcategories);
        List<String> home = new ArrayList<>();
        for (WebElement webElement : hmenu) {
            if (!webElement.getText().isEmpty()) {
                home.add(webElement.getText());
                logger.debug("Captured Home & Kitchen subcategory: " + webElement.getText());
            }
        }
        excelUtil excel = new excelUtil();
        excel.writeList("HomeAndKitchen", home);
        logger.info("Stored Home & Kitchen subcategories in Excel.");

        driver.findElement(closeMenuButton).click();
        logger.info("Closed the hamburger menu.");
    }
}