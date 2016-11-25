import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by numash on 25.11.2016.
 */
public class PlayerTests {
    private static PockerPlayer player = new PockerPlayer();

    public static void createUser(WebDriver driver){
        WebElement insertLink = driver.findElement(By.linkText("Insert"));
        insertLink.click();

        WebElement loginField = driver.findElement(By.xpath("//input[contains(@id, 'login')]"));
        WebElement emailField = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
        WebElement passwordField = driver.findElement(By.xpath("//input[contains(@id, 'password') and not(contains(@id, 'confirm'))]"));
        WebElement confirmPasswordField = driver.findElement(By.xpath("//input[contains(@id, 'confirm_password')]"));
        WebElement fnameField = driver.findElement(By.xpath("//input[contains(@id, 'fname')]"));
        WebElement lnameField = driver.findElement(By.xpath("//input[contains(@id, 'lname')]"));
        WebElement cityField = driver.findElement(By.xpath("//input[contains(@id, 'city')]"));
        WebElement countryField = driver.findElement(By.xpath("//select[contains(@id, 'us_country')]"));
        WebElement addressField = driver.findElement(By.xpath("//textarea[contains(@id, 'us_address')]"));
        WebElement phoneField = driver.findElement(By.xpath("//input[contains(@id, 'us_phone')]"));

        loginField.sendKeys(player.getUsername());
        emailField.sendKeys(player.getEmail());
        passwordField.sendKeys(player.getPassword());
        confirmPasswordField.sendKeys(player.getPassword());
        fnameField.sendKeys(player.getFirstname());
        lnameField.sendKeys(player.getLastname());
        cityField.sendKeys(player.getCity());
        countryField.sendKeys(player.getCountry());
        addressField.sendKeys(player.getAddress());
        phoneField.sendKeys(player.getPhone());

        WebElement saveBtn = driver.findElement(By.name("button_save"));
        saveBtn.click();
    }

    public static void verifyPlayerData(WebDriver driver){
        searchPlayer(driver);
        verifyTableContainsPLayer(driver);
        openPlayerEditWindow(driver);
    }

    private static void searchPlayer(WebDriver driver){
        WebElement username = driver.findElement(By.xpath("//input[contains(@id, 'login') and not(contains(@id, 'last'))]"));
        WebElement searchBtn = driver.findElement(By.name("search"));

        username.sendKeys(player.getUsername());
        searchBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
    }

    private static void verifyTableContainsPLayer(WebDriver driver) {
        try {
            driver.findElement(By.xpath(".//tr[.//a[text()='"
                    + player.getUsername() + "']]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openPlayerEditWindow(WebDriver driver){
        WebElement editLink = driver.findElement(By.xpath(".//tr[.//a[text()='"
                + player.getUsername() + "']]//img[@alt='Edit']/.."));

        editLink.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Insert")));4

        String actualTitle = driver.getTitle();
        String expectedTitle = "Players - Edit";

        try {
            assertText(actualTitle, expectedTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void assertText(String actualValue, String expectedValue) throws Exception {
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected value is "
                    + expectedValue + ", but Actual value is " + actualValue);
            throw new Exception();
        }
    }
}
