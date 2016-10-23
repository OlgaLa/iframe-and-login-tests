import helpers.LoginDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by olapanovich on 23.10.16.
 */
public class LoginTest {

    private static final String BASE_URL = "http://the-internet.herokuapp.com/login";
    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "../chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void reloeadBrowser() {

        driver.get(BASE_URL);
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }

    @Test(dataProviderClass = LoginDataProvider.class, dataProvider = "loginDataProvider")
    public void loginDDTTest(String username, String password, String expectedMessage) {

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector(".radius"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();

        WebElement actualResult = driver.findElement(By.cssSelector("div.flash.error"));
        String[] actualMessage = actualResult.getText().split("\n");

        Assert.assertEquals(actualMessage[0], expectedMessage);
    }
}
