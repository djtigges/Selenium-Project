import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleGoogleTests
{
    private WebDriver driver = new ChromeDriver();

    @Test
    public void verifyPageTitle() {
        // Navigate to the desired website
        driver.get("https://google.com");

        // Verify the title of the page
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        Assertions.assertEquals(actualTitle, expectedTitle, "Page title doesn't match");

        WebElement element = driver.findElement(By.className("gLFyf"));
        element.click();
        element.sendKeys("Fluffy Dogs");
        element.sendKeys(Keys.ENTER);

        WebElement fluffyDogImagesButton = driver.findElement(By.xpath("//*[contains(text(),'Images')]"));
        fluffyDogImagesButton.click();

        driver.quit();
    }
}
