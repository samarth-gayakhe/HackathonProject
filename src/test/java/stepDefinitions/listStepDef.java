package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.AmazonHomePage;
import pages.HamburgerMenuPage;

import static utilities.WebDriverManager.getDriver;

public class listStepDef{
    private AmazonHomePage amazonHomePage;
    private HamburgerMenuPage hamburgerMenuPage;
    private static final Logger logger = LogManager.getLogger(listStepDef.class);
    public listStepDef() {
        amazonHomePage = new AmazonHomePage(getDriver());
        hamburgerMenuPage = new HamburgerMenuPage(getDriver());
    }

    @Given("user clicks on All button on the navbar")
    public void clickOnAllButton(){
        hamburgerMenuPage = amazonHomePage.clickAllHamburgerMenu();
        logger.info("ALl button clicked");
    }

    @And("scrolls down to shop by category and clicks on see all")
    public void scrollsDownToShopByCategoryAndClicksOnSeeAll() {
        hamburgerMenuPage.clickSeeAll();
        logger.info("scrolled down and clicked on see all");
    }

    @Then("click on Home, Kitchen, Pets category")
    public void clickOnHomeKitchenPetsCategory() {
        hamburgerMenuPage.clickHomeKitchenPetsCategory();
        logger.info("clicked on Home, Kitchen, Pets category");
    }

    @And("store the result of Home&Kitchen in excel and close the menu")
    public void storeTheResultOfHomeKitchenInExcel() throws Exception {
        hamburgerMenuPage.storeHomeKitchenResultsAndCloseMenu();
        logger.info("results stored in excel");
    }
}