package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ResultsPage;
import utils.DriverManager;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RestaurantSearchSteps
{
    private HomePage homePage;
    private ResultsPage resultsPage;

    @Given("I want food in {string}")
    public void i_want_food_in(String postcode)
    {
        homePage = new HomePage(DriverManager.getInstance().getDriver());
        homePage.enterPostcode(postcode);
    }

    @When("I search for restaurants")
    public void i_search_for_restaurants()
    {
        resultsPage = homePage.search();
    }

    @Then("I should see some restaurants in {string}")
    public void i_should_see_some_restaurants(String postcode)
    {
        assertTrue("Location does not contain correct postcode", resultsPage.getLocation().contains(postcode));
        assertTrue("No restaurants found", resultsPage.getNumRestaurants() > 0);
    }

    @Given("I am looking at restaurants in an area")
    public void i_am_looking_at_restaurants_in_an_area()
    {
        resultsPage = new ResultsPage(DriverManager.getInstance().getDriver());
        resultsPage.searchForArea("sl4-windsor");
    }

    @When("I filter by {string}")
    public void i_filter_by(String type)
    {
        resultsPage.filterBy(type);
    }

    @Then("I should see restaurants of that {string}")
    public void i_should_see_restaurants_of_that(String type)
    {
        assertTrue("No restaurants found", resultsPage.getNumRestaurants() > 0);
        List<ResultsPage.RestaurantInfo> restaurantInfos = resultsPage.getAllRestaurantInfo();
        for (ResultsPage.RestaurantInfo info : restaurantInfos)
        {
            assertTrue(info.getTypes().contains(type));
        }
    }

    @When("I sort by customer rating")
    public void i_sort_by_customer_rating()
    {
        resultsPage.sortByRating();
    }

    @Then("the results should be sorted in descending order")
    public void results_sorted_in_descending_order()
    {
        assertTrue("No restaurants found", resultsPage.getNumRestaurants() > 0);
        List<ResultsPage.RestaurantInfo> restaurantInfos = resultsPage.getAllRestaurantInfo();
        double rating = restaurantInfos.get(0).getRating();
        // Just check 3 for the sake of the test
        for (int i = 1; i < 3; i++)
        {
            double nextRating = restaurantInfos.get(i).getRating();
            assertTrue(rating >= nextRating);
            rating = nextRating;
        }
    }
}
