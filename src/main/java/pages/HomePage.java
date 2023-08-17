package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage
{
    public static final String HOME_PAGE_URL = "https://www.just-eat.co.uk/";

    private final By searchBar = By.name("postcode");
    private final By searchButton = By.cssSelector("button[data-test-id='find-restaurants-button']");
    private final By addressSuggestions = By.cssSelector("button[data-test-id='full-address-suggestions-item']");
    private final By arbitraryTitle = By.className("Shell_c-searchShell-title_xGHZh");

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    public void enterPostcode(String postcode)
    {
        driver.findElement(searchBar).sendKeys(postcode);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(addressSuggestions));

        // Some suggestions come up based on postcode, don't care about them just want to search the postcode explicitly
        // So click away to get the suggestions to disappear and search button to re-appear
        driver.findElement(arbitraryTitle).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    }

    public ResultsPage search()
    {
        driver.findElement(searchButton).click();
        return new ResultsPage(driver);
    }

}
