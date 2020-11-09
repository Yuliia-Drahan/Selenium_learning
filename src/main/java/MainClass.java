import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yuliia\\IdeaProjects\\testgroup\\drivers\\geckodriver.exe");
//Go to Skelia site
        WebDriver driver = new FirefoxDriver();
        driver.get("https://skelia.com/");

//Find search field
        WebElement searchButton = driver.findElement(By.xpath("//form[@id='site_search']"));
        searchButton.click();

//Check text that displayed in search field
        WebElement searchField = driver.findElement(By.xpath("//input[@id='s']"));
        System.out.println("We have this text on search fields: "+ searchField.getAttribute("placeholder"));

//Put search request to search field and submit result
        searchField.sendKeys("Great QA Team");
        searchButton.submit();

//Implicit wait was added because of problem with result.click(). Because click() was done earlier that element was loaded.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement result = driver.findElement(By.xpath("//span[.='What Makes a Great QA Team: Insider’s Look from Skelia']"));
        result.click();

//Check that we received correct URL
        String URL = driver.getCurrentUrl();
        if (URL.equals("https://skelia.com/articles/what-makes-a-great-qa-team-insiders-look-from-skelia/")) {
            System.out.println("Great job!");
        } else
            System.out.println("Something is wrong. Please, check your code.");

//Close browser
         driver.quit();

    }
}
