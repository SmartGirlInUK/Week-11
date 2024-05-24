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
    public void selectMenu(String menu) {
        WebElement element = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li/div/a[text()='" + menu + "']"));
        element.click();
    }

}
