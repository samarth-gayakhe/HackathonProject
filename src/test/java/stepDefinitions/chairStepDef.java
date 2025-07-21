package stepDefinitions;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.SearchResultsPage;

import static utilities.WebDriverManager.getDriver;

public class chairStepDef{
    private SearchResultsPage searchResultsPage;
    private static final Logger logger = LogManager.getLogger(chairStepDef.class);

    public chairStepDef() {
        searchResultsPage = new SearchResultsPage(getDriver());
    }

    @Then("save the top {int} chair results in excel sheet")
    public void returnTheTopThreeChairs(int count) throws Exception {
        searchResultsPage.saveTopResults("Chairs", count);
        logger.info("Saved the results of chain in excel.");
    }
}