import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by numash on 25.11.2016.
 */

//class which runs the test scenario
public class Environment {
    public static void main(String[] args) {
        //open firefox
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //application url
        String baseUrl = "http://80.92.229.236:81/";

        //create LoginTests and PlayerTests classes objects
        LoginTests loginTests = new LoginTests(driver, baseUrl);
        PlayerTests playerTests = new PlayerTests(driver, baseUrl);
        try{
            //run scenario
            loginTests.run();
            playerTests.run();
        } catch (TestFailedException ex){
            //show fail reason
            System.err.println("Execution Failed due to next reason: " + ex.getMessage());
        }
        //close browser
        driver.quit();
    }
}
