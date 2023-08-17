package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager
{
    private static final DriverManager theInstance = new DriverManager();

    private WebDriver driver;

    private DriverManager()
    {

    }

    public static DriverManager getInstance()
    {
        return theInstance;
    }

    public WebDriver getDriver()
    {
        if (driver == null)
        {
            createDriver();
        }
        return driver;
    }

    private void createDriver()
    {
        driver = new ChromeDriver();
    }

    public void quit()
    {
        driver.quit();
        driver = null;
    }
}
