import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by numash on 23.11.2016.
 */
public class LoginTests {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String url = "http://80.92.229.236:81/";
        driver.get(url + "auth/login");

        String username  = "admin";
        String password = "123";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.id("logIn"));
        loginBtn.click();

        //WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id, 'login')]")));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Players";

        if (actualTitle.equals(expectedTitle)){
            System.out.println("Passed");
        } else{
            System.err.println("Failed. Expected title is " + expectedTitle
                + " but Actual title is " + actualTitle);
        }

        driver.quit();
    }
}
