package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.GiftCardPage;
import pages.SearchResultsPage;

import java.time.Duration;

import static utilities.WebDriverManager.getDriver;

public class giftcardStepDef{
    private SearchResultsPage searchResultsPage;
    private GiftCardPage giftCardPage;
    private static final Logger logger = LogManager.getLogger(giftcardStepDef.class);
    public giftcardStepDef() {
        searchResultsPage = new SearchResultsPage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        giftCardPage = new GiftCardPage(getDriver());
    }

    @Given("Clicks on the relevant result")
    public void clicksOnTheFirstResult(){
        giftCardPage = searchResultsPage.clickFirstResult();
        logger.info("click on relevant result");
    }

    @And("selects happy birthday")
    public void selectsHappyBirthday() {
        giftCardPage.selectHappyBirthdayDesign();
        logger.info("birthday card selected");
    }

    @And("clicks on send as email")
    public void clicksOnSendAsEmail() {
        giftCardPage.clickSendAsEmail();
        logger.info("send as email selected");
    }

    @And("enter invalid email {string} and sender name {string}")
    public void enterInvalidEmail(String email, String senderName) {
        giftCardPage.enterInvalidEmail(email, senderName);
        logger.info("invalid email and senders name entered");
    }
    @Then("User should see an error message")
    public void userShouldSeeAErrorMessage() {
        giftCardPage.verifyErrorMessage();
        logger.info("error message verified");
    }
}