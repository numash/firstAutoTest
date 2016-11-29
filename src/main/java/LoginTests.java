import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by numash on 23.11.2016.
 */
//implements login methods
public class LoginTests extends BaseTests{

    //constructor based on parent class BaseTests
    public LoginTests(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    //runs login method
    public void run() throws TestFailedException{
        login();
    }

    //logs
    private void login() throws TestFailedException{
        driver.get(baseUrl + "auth/login");
        //compares titles
        assertResult("LoginTests", "login", "Login", driver.getTitle());

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

        //compares titles
        assertResult("LoginTests", "login", expectedTitle, actualTitle);
    }
}
