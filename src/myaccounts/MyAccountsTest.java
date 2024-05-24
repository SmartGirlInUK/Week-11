package myaccounts;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Before;

public class MyAccountsTest extends BaseTest {
    String url = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(url);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
