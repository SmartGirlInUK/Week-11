package myaccounts;

import browserTesting.BaseTest;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyAccountsTest extends BaseTest {
    String url = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(url);
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully(){
        driver.findElement(By.className("dropdown")).click();
        selectMyAccountOptions("Register");
        String actualText=driver.findElement(By.cssSelector("div#content h1")).getText();
        Assert.assertEquals("Validating user is on Register Page","Register Account",actualText);
    }
    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully(){
        driver.findElement(By.className("dropdown")).click();
        selectMyAccountOptions("Login");
        String actualText=driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/h2")).getText();
        Assert.assertEquals("Validating user is on Login Page","Returning Customer",actualText);
    }
    @Test
    public void verifyThatUserRegisterAccountSuccessfully(){
        driver.findElement(By.className("dropdown")).click();
        selectMyAccountOptions("Register");
        driver.findElement(By.id("input-firstname")).sendKeys("Admin");
        driver.findElement(By.name("lastname")).sendKeys("abcd");
        driver.findElement(By.id("input-email")).sendKeys("admin6511@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("08564965846");
        driver.findElement(By.name("password")).sendKeys("Admin@abcd");
        driver.findElement(By.id("input-confirm")).sendKeys("Admin@abcd");
        driver.findElement(By.xpath("//label[@class='radio-inline']/input[@value='1']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")).submit();
        String actualText=driver.findElement(By.cssSelector("div#content h1")).getText();
        Assert.assertEquals("Validating user is register successfully.","Your Account Has Been Created!",actualText);
        driver.findElement(By.linkText("Continue")).click();
        driver.findElement(By.className("dropdown")).click();
        selectMyAccountOptions("Logout");
        String actualText1=driver.findElement(By.cssSelector("div#content h1")).getText();
        Assert.assertEquals("Validating user is logged out.","Account Logout",actualText1);
        driver.findElement(By.linkText("Continue")).click();

    }

    @After
    public void tearDown() {
        //closeBrowser();
    }

    public void selectMyAccountOptions(String option) {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'"+option+"')]"));
        element.click();
    }

}
