import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by olapanovich on 23.10.16.
 */
public class IFrameTest {

    private static final String BASE_URL= "http://the-internet.herokuapp.com/iframe";
    private WebDriver driver;
    private static final String PRINT_TEXT = "Hello!";


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "../chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }

    @Test
    public void iFrameTest() {

        WebElement boldButton = driver.findElement(By.cssSelector("div[aria-label='Bold']>button"));
        boldButton.click();

        driver.switchTo().frame("mce_0_ifr");

        WebElement element = driver.findElement(By.cssSelector("body"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].innerHTML = '<p>Hello!</p>'", element);

        WebElement textInput = driver.findElement(By.xpath(".//*[@id='tinymce']/p"));

        //textInput.clear();
        //textInput.click();
        //textInput.sendKeys(PRINT_TEXT);
        Assert.assertEquals(textInput.getText(), PRINT_TEXT);

        driver.switchTo().defaultContent();
    }


}
