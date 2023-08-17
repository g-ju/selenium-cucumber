package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ResultsPage;
import utils.DriverManager;

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
        assertTrue("No restaurants found", resultsPage.getRestaurants().size() > 0);
    }
}
