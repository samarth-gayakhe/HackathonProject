package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.GiftCardPage;
import pages.SearchResultsPage;

import java.time.Duration;

import static utilities.WebDriverManager.getDriver;

public class giftcardStepDef{
    private SearchResultsPage searchResultsPage;
    private GiftCardPage giftCardPage;

    public giftcardStepDef() {
        searchResultsPage = new SearchResultsPage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        giftCardPage = new GiftCardPage(getDriver());
    }

    @Given("Clicks on the first result")
    public void clicksOnTheFirstResult(){
        giftCardPage = searchResultsPage.clickFirstResult();
    }

    @And("selects happy birthday")
    public void selectsHappyBirthday() {
        giftCardPage.selectHappyBirthdayDesign();
    }

    @And("clicks on send as email")
    public void clicksOnSendAsEmail() {
        giftCardPage.clickSendAsEmail();
    }

    @And("enter invalid email {string} and sender name {string}")
    public void enterInvalidEmail(String email, String senderName) {
        giftCardPage.enterInvalidEmail(email, senderName);
    }
    @Then("User should see an error message")
    public void userShouldSeeAErrorMessage() {
        giftCardPage.verifyErrorMessage();
    }
}