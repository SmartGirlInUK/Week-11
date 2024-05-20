package browserTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver = new ChromeDriver();

    public void openBrowser(String baseUrl) {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(400));
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

}
