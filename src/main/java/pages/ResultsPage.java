package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage
{
    private static final String RESULT_PAGE_PREFIX = "https://www.just-eat.co.uk/area/";

    private final By locationHeader = By.className("c-locationHeader-title");
    private final By restaurantCard = By.cssSelector("a[data-test-id='f-restaurant-card--restaurant-card-component']");
    private final By filterBox = By.className("FilterPill_c-filterPill-text_2RTiU");
    private final By sortOptions = By.cssSelector("span[data-test-id='sort_option_title']");
    private final By ratingSortOption = By.cssSelector("label[for='Avg_Rating']");

    public ResultsPage(WebDriver driver)
    {
        super(driver);
    }

    public void searchForArea(String area)
    {
        driver.get(RESULT_PAGE_PREFIX + area);
    }

    public String getLocation()
    {
        return driver.findElement(locationHeader).getText();
    }

    public int getNumRestaurants()
    {
        return driver.findElements(restaurantCard).size();
    }

    public List<RestaurantInfo> getAllRestaurantInfo()
    {
        List<WebElement> restaurantElements = driver.findElements(restaurantCard);

        return restaurantElements.stream()
                                 .map(RestaurantInfo::new)
                                 .toList();
    }

    public void filterBy(String type)
    {
        List<WebElement> filterBoxes = driver.findElements(filterBox);
        for(WebElement box : filterBoxes)
        {
            if (box.getText().equals(type))
            {
                box.click();
                break;
            }
        }
    }

    public void sortByRating()
    {
        WebElement sortOptionTitle = driver.findElement(sortOptions);
        Actions actions = new Actions(driver);
        actions.moveToElement(sortOptionTitle).perform();

        WebElement ratingOption = driver.findElement(ratingSortOption);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ratingOption));
        ratingOption.click();
    }

    public static class RestaurantInfo
    {
        private final String name;
        private final List<String> types;
        private final double rating;

        RestaurantInfo(WebElement restaurantElement)
        {
            name = restaurantElement.findElement(By.cssSelector("h3[data-test-id='restaurant_name']")).getText();
            types = restaurantElement.findElements(
                    By.className("RestaurantCuisines_c-restaurantCard-cuisines-item_3ZQ0v"))
                                     .stream()
                                     .map(WebElement::getText)
                                     .collect(Collectors.toList());
            rating = Double.parseDouble(restaurantElement.findElement(By.cssSelector("data[data-test-id='ratings-mean-value']")).getText());
        }

        public String getName()
        {
            return name;
        }

        public List<String> getTypes()
        {
            return types;
        }

        public double getRating() {
            return rating;
        }
    }
}
