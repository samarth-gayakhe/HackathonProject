package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.AmazonHomePage;
import pages.HamburgerMenuPage;

import static utilities.WebDriverManager.getDriver;

public class listStepDef{
    private AmazonHomePage amazonHomePage;
    private HamburgerMenuPage hamburgerMenuPage;

    public listStepDef() {
        amazonHomePage = new AmazonHomePage(getDriver());
        hamburgerMenuPage = new HamburgerMenuPage(getDriver());
    }

    @Given("user clicks on All button on the navbar")
    public void clickOnAllButton(){
        hamburgerMenuPage = amazonHomePage.clickAllHamburgerMenu();
    }

    @And("scrolls down to shop by category and clicks on see all")
    public void scrollsDownToShopByCategoryAndClicksOnSeeAll() {
        hamburgerMenuPage.clickSeeAll();
    }

    @Then("click on Home, Kitchen, Pets category")
    public void clickOnHomeKitchenPetsCategory() {
        hamburgerMenuPage.clickHomeKitchenPetsCategory();
    }

    @And("store the result of Home&Kitchen in excel and close the menu")
    public void storeTheResultOfHomeKitchenInExcel() throws Exception {
        hamburgerMenuPage.storeHomeKitchenResultsAndCloseMenu();
    }
}