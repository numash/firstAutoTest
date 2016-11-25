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
        WebDriverWait wait = new WebDriverWait(driver, 20);
    }

    public static void verifyPlayerData(WebDriver driver){
        searchPlayer(driver);
        verifyTableContainsPLayer(driver);
        openPlayerEditWindow(driver);

        WebElement loginField = driver.findElement(By.xpath("//input[contains(@id, 'login')]"));
        WebElement emailField = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
        WebElement fnameField = driver.findElement(By.xpath("//input[contains(@id, 'fname')]"));
        WebElement lnameField = driver.findElement(By.xpath("//input[contains(@id, 'lname')]"));
        WebElement cityField = driver.findElement(By.xpath("//input[contains(@id, 'us_city')]"));
        WebElement countryField = driver.findElement(By.xpath("//select[contains(@id, 'us_country')]/option[@selected='selected']"));
        WebElement addressField = driver.findElement(By.xpath("//textarea[contains(@id, 'us_address')]"));
        WebElement phoneField = driver.findElement(By.xpath("//input[contains(@id, 'us_phone')]"));

        verifyText(loginField.getAttribute("value"), player.getUsername());
        verifyText(emailField.getAttribute("value"), player.getEmail());
        verifyText(fnameField.getAttribute("value"), player.getFirstname());
        verifyText(lnameField.getAttribute("value"), player.getLastname());
        verifyText(cityField.getAttribute("value"), player.getCity());
        verifyText(countryField.getText(), player.getCountry());
        verifyText(addressField.getAttribute("value"), player.getAddress());
        verifyText(phoneField.getAttribute("value"), player.getPhone());
    }

    private static void searchPlayer(WebDriver driver){
        try {
            assertText(driver.getTitle(), "Players");
        }catch(Exception e){
            e.printStackTrace();
        }

        WebElement username = driver.findElement(By.xpath("//input[contains(@id, 'login') and not(contains(@id, 'last'))]"));
        WebElement searchBtn = driver.findElement(By.name("search"));

        username.clear();
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
        //wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Insert")));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Players - Edit";

        try {
            assertText(actualTitle, expectedTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void EditPlayer(WebDriver driver){

        player.setEmail(player.getEmail() + ".ua");
        player.setFirstname("first_name");
        player.setLastname("last_name");
        player.setCity("new-City.");
        player.setCountry("ARUBA");
        player.setAddress(player.getAddress() + " 1/68");
        player.setPhone("9(2)263;");

        WebElement emailField = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
        WebElement fnameField = driver.findElement(By.xpath("//input[contains(@id, 'fname')]"));
        WebElement lnameField = driver.findElement(By.xpath("//input[contains(@id, 'lname')]"));
        WebElement cityField = driver.findElement(By.xpath("//input[contains(@id, 'us_city')]"));
        WebElement countryField = driver.findElement(By.xpath("//select[contains(@id, 'us_country')]"));
        WebElement addressField = driver.findElement(By.xpath("//textarea[contains(@id, 'us_address')]"));
        WebElement phoneField = driver.findElement(By.xpath("//input[contains(@id, 'us_phone')]"));

        emailField.clear();
        emailField.sendKeys(player.getEmail());
        fnameField.clear();
        fnameField.sendKeys(player.getFirstname());
        lnameField.clear();
        lnameField.sendKeys(player.getLastname());
        cityField.clear();
        cityField.sendKeys(player.getCity());
        countryField.sendKeys(player.getCountry());
        addressField.clear();
        addressField.sendKeys(player.getAddress());
        phoneField.clear();
        phoneField.sendKeys(player.getPhone());

        WebElement saveBtn = driver.findElement(By.name("button_save"));
        saveBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
    }

    private static void assertText(String actualValue, String expectedValue) throws Exception {
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected value is "
                    + expectedValue + ", but Actual value is " + actualValue);
            throw new Exception();
        }
    }

    private static void verifyText(String actualValue, String expectedValue){
        if(actualValue.equals(expectedValue)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected value is "
                    + expectedValue + ", but Actual value is " + actualValue);
        }
    }
}
