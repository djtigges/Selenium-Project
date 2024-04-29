import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTests
{
    private WebDriver driver;

    @Test
    public void routingTests()
    {
        String expectedTitle = "Schedule of Classes: Iowa State University";
        String actualTitle = driver.getTitle();

        WebElement plannerButton = driver.findElement(By.xpath("//*[contains(text(),'Planner')]"));
        plannerButton.click();

        String plannerExpectedTitle = "Course Planner: Iowa State University";
        String plannerActualTitle = driver.getTitle();

        WebElement returnButton = driver.findElement(By.xpath("//*[contains(text(),'Return to Schedule of Classes')]"));
        returnButton.click();
        String ActualReturnToPlannerPageTitle = "Schedule of Classes: Iowa State University";
        String returnButtonTitle = driver.getTitle();

        Assertions.assertAll(
            () -> Assertions.assertEquals(actualTitle, expectedTitle, "Page title doesn't match"),
            () -> Assertions.assertEquals(plannerActualTitle, plannerExpectedTitle, "Page title doesn't match"),
            () -> Assertions.assertEquals(ActualReturnToPlannerPageTitle, returnButtonTitle, "Page title doesn't match")
        );

        driver.quit();
    }

    @Test
    public void advancedToggleVisibilityTest()
    {
        WebElement advancedSearchForm = driver.findElement(By.id("advancedSearchOptions"));
        Assertions.assertFalse(advancedSearchForm.isDisplayed(),"Advanced options should not be displayed on page startup");

        WebElement toggleAdvancedSearchButton = driver.findElement(By.id("advancedToggle"));
        toggleAdvancedSearchButton.click();

        Assertions.assertTrue(advancedSearchForm.isDisplayed(), "Advanced options didn't appear when toggled");

        WebElement plannerButton = driver.findElement(By.xpath("//*[contains(text(),'Planner')]"));
        plannerButton.click();
        WebElement returnButton = driver.findElement(By.xpath("//*[contains(text(),'Return to Schedule of Classes')]"));
        returnButton.click();

        // This test sometimes doesn't pass because the page doesn't finish before checking if the element
        // is visible. I think this would be great to throw in our analysis on test flakiness as a potential downside to selenium
        Assertions.assertFalse(advancedSearchForm.isDisplayed(), "Advanced options should not be visible when returning to the class schedule page");
    }

    @BeforeEach
    public void goToScheduleOfClasses()
    {
        driver = new ChromeDriver();
        driver.get("https://classes.iastate.edu/");
    }
    @AfterEach
    public void quit()
    {
        driver.quit();
    }
}
