package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GiftCardPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(GiftCardPage.class);

    private By happyBirthdayDesign = By.xpath("//*[@id='gc-mini-picker-design-swatch-image-2']");
    private By sendAsEmailButton = By.xpath("//*[@id='gc-delivery-mechanism-button-Email-announce']");
    private By emailsInput = By.xpath("//*[@name= 'emails']");
    private By senderNameInput = By.xpath("//*[@id='gc-order-form-senderName']");
    private By emailErrorMessage = By.xpath("//div[@class='a-alert-content']//strong/parent::div");

    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectHappyBirthdayDesign() {
        driver.findElement(happyBirthdayDesign).click();
        logger.info("Selected 'Happy Birthday' design.");
    }

    public void clickSendAsEmail() {
        driver.findElement(sendAsEmailButton).click();
        logger.info("Clicked 'Send as Email' button.");
    }

    public void enterInvalidEmail(String email, String senderName) {
        driver.findElement(emailsInput).sendKeys(email);
        driver.findElement(senderNameInput).sendKeys(senderName);
        logger.info("Entered invalid email: " + email + " and sender name: " + senderName);
    }

    public void verifyErrorMessage() {
        String errorMessage = driver.findElement(emailErrorMessage).getText();
        Assert.assertFalse(errorMessage.isEmpty());
        logger.info("Verified error message is displayed: " + errorMessage);
    }
}
