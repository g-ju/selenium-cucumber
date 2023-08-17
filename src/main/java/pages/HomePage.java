package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage
{
    public static final String HOME_PAGE_URL = "https://www.just-eat.co.uk";

    public HomePage(WebDriver driver)
    {
        super(driver);
    }
}
