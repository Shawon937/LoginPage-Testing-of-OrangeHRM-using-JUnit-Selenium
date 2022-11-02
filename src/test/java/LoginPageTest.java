import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPageTest {
    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void _1_getTitle(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String titleActual= driver.getTitle();
        String titleExpected="OrangeHRM";
        System.out.println(titleActual);
        Assert.assertEquals(titleExpected,titleActual);
    }

    @Test
    public void _2_checkIfImageExists() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        List<WebElement> imageElem= driver.findElements(By.tagName("img"));

        //Utils.explicitWaitForTheElement(driver, imageElem.get(0), 32 );
        Thread.sleep(2000);
        Assert.assertTrue(imageElem.get(0).isDisplayed());

        //Utils.explicitWaitForTheElement(driver, imageElem.get(2), 32 );
        Thread.sleep(2000);
        Assert.assertTrue(imageElem.get(2).isDisplayed());
    }

    @Test
    public void _3_loginWithValidCreds() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebElement userElem = driver.findElement(By.cssSelector("[name=username]"));
        userElem.sendKeys("Admin");

        WebElement passElem = driver.findElement(By.cssSelector("[name=password]"));
        passElem.sendKeys("admin123");
        passElem.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //Redirect to new Page
        WebElement profileNameElem = driver.findElement(By.className("oxd-table-filter-title"));

//        String employeInformationTxt= profileNameElem.getText();
//        System.out.println(employeInformationTxt);
//        Assert.assertEquals("Employee Information", employeInformationTxt);

        Assert.assertTrue(profileNameElem.isDisplayed());
    }

    @Test
    public void _4_loginWithInvalidCreds() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement userElem = driver.findElement(By.cssSelector("[name=username]"));
        userElem.sendKeys("Admi");

        WebElement passElem = driver.findElement(By.cssSelector("[name=password]"));
        passElem.sendKeys("adm23");
        passElem.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        WebElement invalidCredsElem = driver.findElement(By.className("oxd-alert-content-text"));
        String invalidCredsTxt= invalidCredsElem.getText();
        System.out.println(invalidCredsTxt);
        Assert.assertEquals("Invalid credentials", invalidCredsTxt);
    }


    @After
    public void close(){
       driver.close();
    }

}
