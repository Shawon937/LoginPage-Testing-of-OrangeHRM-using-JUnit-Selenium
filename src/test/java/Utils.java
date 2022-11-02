import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Utils {
    public static void explicitWaitForTheElement(WebDriver driver, WebElement webElement, int SECOND ){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(SECOND));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}
