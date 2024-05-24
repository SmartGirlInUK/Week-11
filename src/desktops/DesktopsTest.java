package desktops;

import browserTesting.BaseTest;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends BaseTest {
    String url = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(url);
    }

    @Test
    public void productArrangeInOrder() {
        deskTop();
        WebElement dropDown = driver.findElement(By.id("input-sort"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name (Z - A)");
        List<WebElement> listOfProducts = driver.findElements(By.cssSelector("h4"));
        List<String> tempList = new ArrayList<>();
        for (WebElement list : listOfProducts) {
            tempList.add(list.getText().toLowerCase());
        }
        List<String> list = new ArrayList<>(tempList);
        tempList.sort(Collections.reverseOrder());
        Assert.assertEquals(tempList, list);
    }

    @Test
    public void productAddToCart() throws InterruptedException {
        deskTop();
        WebElement dropDown = driver.findElement(By.id("input-sort"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name (A - Z)");
        driver.findElement(By.xpath("//div[@class='caption']/h4/a[text()='HP LP3065']")).click();
        String actualOutput = driver.findElement(By.xpath("//div[@class='col-sm-4']/h1")).getText();
        Assert.assertEquals("Validating the Text - HP LP3065", "HP LP3065", actualOutput);
        driver.findElement(By.id("input-option225")).sendKeys("2022-11-30");
        driver.findElement(By.id("input-quantity")).clear();
        driver.findElement(By.id("input-quantity")).sendKeys("1");
        driver.findElement(By.id("button-cart")).click();
        Thread.sleep(200);
        String actualMessage = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible")).getText();
        Assert.assertEquals("", "Success: You have added HP LP3065 to your shopping cart!\n×", actualMessage);
        driver.findElement(By.xpath("//div[@id='product-product']/div[1]/a[2]")).click();
        String actualText = driver.findElement(By.cssSelector("div#content h1")).getText();
        Assert.assertEquals("Shopping Cart Message Validation", "Shopping Cart  (1.00kg)", actualText);

        String message1 = driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[2]/a")).getText();
        Assert.assertEquals("HP LP3065 Message Validation", "HP LP3065", message1);

        String message2 = driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[2]/small[1]")).getText();
        Assert.assertEquals("Delivery Date Message Validation", "Delivery Date:2011-04-22", message2);

        String message3 = driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[6]")).getText();
        Assert.assertEquals("Total Amount Message Validation", "$122.00", message3);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    public void deskTop() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[1]/a"))).click().build().perform();
        selectMenu("Show AllDesktops");
    }
}