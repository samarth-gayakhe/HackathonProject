package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.SearchResultsPage;

import static utilities.WebDriverManager.getDriver;

public class chairStepDef{
    private SearchResultsPage searchResultsPage;

    public chairStepDef() {
        searchResultsPage = new SearchResultsPage(getDriver());
    }

    @Then("save the top {int} chair results in excel sheet")
    public void returnTheTopThreeChairs(int count) throws Exception {
        searchResultsPage.saveTopResults("Chairs", count);
    }
}