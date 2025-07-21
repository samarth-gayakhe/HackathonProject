package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import pages.AmazonHomePage;
import pages.SearchResultsPage;
import utilities.LoggerManager;
import org.apache.logging.log4j.Logger;

import static utilities.WebDriverManager.getDriver;

public class bookshelvesStepDef{
    private static final Logger logger = LogManager.getLogger(bookshelvesStepDef.class);
    private AmazonHomePage amazonHomePage;
    private SearchResultsPage searchResultsPage;
    public bookshelvesStepDef() {
        amazonHomePage = new AmazonHomePage(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());
    }

    @And("filter the results with price less than {string} and include out of stock")
    public void filterTheResultsBasedOnPrice(String price) {
        searchResultsPage.filterResultsByPrice(price);
        searchResultsPage.includeOutOfStock();
        logger.info("filtered the results");
    }

    @Then("save the top {int} results in excel sheet")
    public void saveTheTopResults(int count) throws Exception {
        searchResultsPage.saveTopResults("Bookshelves", count);
        logger.info("Saved the results in excel.");
    }
}