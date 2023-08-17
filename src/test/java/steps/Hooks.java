package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverManager;

public class Hooks
{
    private WebDriver driver;

    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        driver = DriverManager.getInstance().getDriver();
        driver.get(HomePage.HOME_PAGE_URL);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
