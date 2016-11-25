import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by numash on 25.11.2016.
 */
public class RunTests {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://80.92.229.236:81/";

        LoginTests.Login(driver, baseUrl);
        PlayerTests.createUser(driver);
        PlayerTests.verifyPlayerData(driver);

        driver.quit();
    }
}
