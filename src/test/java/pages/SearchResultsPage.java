package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import utilities.excelUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class);

    private By searchResultsTitle = By.xpath("//span[contains(@class, 'a-color-state a-text-bold')]");
    private By priceSliderForm = By.cssSelector("form[data-slider-id='p_36/range-slider']");
    private By goButton = By.cssSelector("input[type='submit'][aria-label='Go - Submit price range']");
    private By includeOutOfStockCheckbox = By.xpath("//a[.//span[text()='Include Out of Stock']]");
    private By productCards = By.xpath("//div[contains(@class, 'puis-card-container')]");


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void filterResultsByPrice(String price) {
        WebElement sliderElement = driver.findElement(priceSliderForm);
        String rawJson = sliderElement.getAttribute("data-slider-props").replace("&quot;", "\"");

        int start = rawJson.indexOf("[");
        int end = rawJson.indexOf("]", start);

        String stepValuesString = rawJson.substring(start + 1, end);
        String[] stepValuesArray = stepValuesString.split(",");

        List<Integer> stepValues = new ArrayList<>();
        for (String value : stepValuesArray) {
            value = value.trim();
            if (!value.equals("null") && !value.isEmpty()) {
                stepValues.add(Integer.parseInt(value));
            }
        }
        int target = Integer.parseInt(price);
        int upperSliderValue = 0;
        for (int i = 0; i < stepValues.size(); i++) {
            if (stepValues.get(i) > target) {
                upperSliderValue = i;
                break;
            }
        }

        WebElement upperSlider = driver.findElement(By.id("p_36/range-slider_slider-item_upper-bound-slider"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", upperSlider, upperSliderValue + 1);

        driver.findElement(goButton).click();
        logger.info("Filtered results with price less than: " + price);
    }

    public void includeOutOfStock() {
        driver.findElement(includeOutOfStockCheckbox).click();
        logger.info("Clicked 'Include Out of Stock' checkbox.");
    }

    public void saveTopResults(String sheetName, int count) throws Exception {
        List<WebElement> arr = driver.findElements(productCards);
        excelUtil excel = new excelUtil();
        List<String> titles = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        for(int i = 0; i < count && i < arr.size(); i++) {
            String str = arr.get(i).getText();
            String[] list = str.split("\n");
            String title = "";
            String price = "";
            for(int j = 0; j < list.length; j++) {
                if(list[j].equalsIgnoreCase("Sponsored")) {
                    title = list[j+1];
                }
                if(list[j].equals("M.R.P:") || list[j].equals("Deal of the Day") || list[j].equals("Save")) {
                    price = list[j-1];
                }
            }
            if(title.isEmpty()){
                title = list[0];
            }
            titles.add(title.trim());
            prices.add(price.trim());
            logger.info("Captured result - Title: " + title + ", Price: " + price);
        }
        excel.writeTitlePrice(sheetName, titles, prices);
        logger.info("Saved top " + count + " results to Excel sheet: " + sheetName);
    }

    public GiftCardPage clickFirstResult() {
        driver.findElement(By.xpath("//*[@role='listitem']//span[contains(text(), 'Amazon')]")).click();
        logger.info("Clicked on the first search result.");
        return new GiftCardPage(driver);
    }
}