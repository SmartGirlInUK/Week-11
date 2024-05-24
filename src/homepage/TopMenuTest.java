package homepage;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TopMenuTest extends BaseTest {
    String url = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(url);
    }

    @Test
    public void topMenu() {
        Actions actions = new Actions(driver);
        List<WebElement> linkOfTabs = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']/li/a"));
        for (int i = 0; i < 3; i++) {
            actions.moveToElement(linkOfTabs.get(i)).click().build().perform();
            if (linkOfTabs.get(i).getText().equalsIgnoreCase("Desktops")) {
                selectMenu("Show AllDesktops");
                String actualOutput = driver.findElement(By.cssSelector("h2")).getText();
                Assert.assertEquals("Desktops Message Validation", "Desktops", actualOutput);
                driver.navigate().back();
            }
            if (linkOfTabs.get(i).getText().equalsIgnoreCase("Laptops & Notebooks")) {
                selectMenu("Show AllLaptops & Notebooks");
                String actualOutput = driver.findElement(By.cssSelector("h2")).getText();
                Assert.assertEquals("Laptops & Notebooks Message Validation", "Laptops & Notebooks", actualOutput);
                driver.navigate().back();
            }
            if (linkOfTabs.get(i).getText().equalsIgnoreCase("Components")) {
                selectMenu("Show AllComponents");
                String actualOutput = driver.findElement(By.cssSelector("h2")).getText();
                Assert.assertEquals("Components Message Validation", "Components", actualOutput);
                driver.navigate().back();
            }
        }
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
