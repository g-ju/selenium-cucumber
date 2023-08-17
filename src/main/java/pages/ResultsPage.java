package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage extends BasePage
{
    private final By locationHeader = By.className("c-locationHeader-title");
    private final By restaurantCard = By.cssSelector("a[data-test-id='f-restaurant-card--restaurant-card-component']");

    public ResultsPage(WebDriver driver)
    {
        super(driver);
    }

    public String getLocation()
    {
        return driver.findElement(locationHeader).getText();
    }

    public List<WebElement> getRestaurants()
    {
        return driver.findElements(restaurantCard);
    }
}
