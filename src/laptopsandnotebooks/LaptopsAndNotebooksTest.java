package laptopsandnotebooks;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LaptopsAndNotebooksTest extends BaseTest {
    String url = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(url);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement element=driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[2]"));
        actions.moveToElement(element).click().build().perform();
        selectMenu("Show AllLaptops & Notebooks");
        WebElement dropDown = driver.findElement(By.id("input-sort"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Price (High > Low)");
        driver.findElement(By.linkText("MacBook")).click();
        driver.findElement(By.id("button-cart")).click();
        Thread.sleep(200);
        String actualMessage = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible")).getText();
        Assert.assertEquals("Validating ", "Success: You have added MacBook to your shopping cart!\n×", actualMessage);
        driver.findElement(By.xpath("//div[@id='product-product']/div[1]/a[2]")).click();
        String actualText = driver.findElement(By.cssSelector("div#content h1")).getText();
        junit.framework.Assert.assertEquals("Shopping Cart Message Validation", "Shopping Cart  (0.00kg)", actualText);

        String message1 = driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[2]/a")).getText();
        junit.framework.Assert.assertEquals("MacBook Message Validation", "MacBook", message1);

        driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[4]/div/input")).clear();
        driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[4]/div/input")).sendKeys("2");
        driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[4]/div/span/button[1]")).click();
        String actual = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible")).getText();
        Assert.assertEquals("Validating ", "Success: You have modified your shopping cart!\n×", actual);
        String message = driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[6]")).getText();
        Assert.assertEquals("Total Amount Message Validation", "$1,204.00", message);
        driver.findElement(By.linkText("Checkout")).click();

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
