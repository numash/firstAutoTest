import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by numash on 23.11.2016.
 */
public class LoginTests {

    /*public static void assertText(String actualValue, String expectedValue) {
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected value is "
                + expectedValue + ", but Actual value is " + actualValue);
        }
    }*/

    public static void assertText(String actualValue, String expectedValue) throws Exception {
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected value is "
                    + expectedValue + ", but Actual value is " + actualValue);
            throw new Exception();
        }
    }

    public static void Login(WebDriver driver, String url){
        driver.get(url + "auth/login");

        String username  = "admin";
        String password = "123";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.id("logIn"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Insert")));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Players";

        try {
            assertText(actualTitle, expectedTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
