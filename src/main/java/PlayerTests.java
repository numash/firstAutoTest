import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by numash on 25.11.2016.
 */
//implements player functionality such as create user, edit user, verify user data and so on
public class PlayerTests extends BaseTests {
    //create a new PokerPlayer with random data
    private static PokerPlayer player = PokerPlayer.CreateRandomPockerPlayer();

    //constructor based on parent class BaseTests
    public PlayerTests(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    //inserts player in system
    public void createUser() throws TestFailedException{
        //compres titles
        assertResult("PlayerTests", "searchPlayer", "Players", driver.getTitle());

        WebElement insertLink = driver.findElement(By.linkText("Insert"));
        insertLink.click();

        fillCreateUserForm(player, "pass_Word68");

        WebElement saveBtn = driver.findElement(By.name("button_save"));
        saveBtn.click();
    }

    //compares expected (initial) player and actual player
    public void verifyPlayerData() throws TestFailedException{
        searchPlayer();
        assertTableContainsPlayer();
        openPlayerEditWindow();

        PokerPlayer actualPlayer = createPockerPlayerFromForm();
        verifyResult("PlayerTests", "verifyPlayerData", player, actualPlayer);
    }

    //changes player data in edit player window
    public void editPlayer() throws TestFailedException{
        searchPlayer();
        assertTableContainsPlayer();
        openPlayerEditWindow();

        player.setEmail(player.getEmail() + ".ua");
        player.setFirstname("first_name");
        player.setLastname("last_name");
        player.setCity("new-City.");
        player.setCountry("ARUBA");
        player.setAddress(player.getAddress() + " 1/68");
        player.setPhone("9(2)263;");

        clearAndFillFieldWithValue("//input[contains(@id, 'email')]", player.getEmail());
        clearAndFillFieldWithValue("//input[contains(@id, 'fname')]", player.getFirstname());
        clearAndFillFieldWithValue("//input[contains(@id, 'lname')]", player.getLastname());
        clearAndFillFieldWithValue("//input[contains(@id, 'us_city')]", player.getCity());
        fillFieldWithValue("//select[contains(@id, 'us_country')]", player.getCountry());
        clearAndFillFieldWithValue("//textarea[contains(@id, 'us_address')]", player.getAddress());
        clearAndFillFieldWithValue("//input[contains(@id, 'us_phone')]", player.getPhone());

        WebElement saveBtn = driver.findElement(By.name("button_save"));
        saveBtn.click();
    }

    //runs test scenario
    public void run() throws TestFailedException {
        createUser();
        verifyPlayerData();
        editPlayer();
        verifyPlayerData();
    }


    //finds an element by xpath, clears it and fills it with value
    private void clearAndFillFieldWithValue(String fieldXpath, String value){
        WebElement field = driver.findElement(By.xpath(fieldXpath));
        field.clear();
        field.sendKeys(value);
    }

    //finds an element by xpath and fills it with value
    private void fillFieldWithValue(String fieldXpath, String value){
        WebElement field = driver.findElement(By.xpath(fieldXpath));
        field.sendKeys(value);
    }


    //is used in createUser() method. Fills the fields with player data
    private void fillCreateUserForm(PokerPlayer player, String password){
        clearAndFillFieldWithValue("//input[contains(@id, 'login')]", player.getUsername());
        clearAndFillFieldWithValue("//input[contains(@id, 'email')]", player.getEmail());
        clearAndFillFieldWithValue("//input[contains(@id, 'password') and not(contains(@id, 'confirm'))]", password);
        clearAndFillFieldWithValue("//input[contains(@id, 'confirm_password')]", password);
        clearAndFillFieldWithValue("//input[contains(@id, 'fname')]", player.getFirstname());
        clearAndFillFieldWithValue("//input[contains(@id, 'lname')]", player.getLastname());
        clearAndFillFieldWithValue("//input[contains(@id, 'city')]", player.getCity());
        fillFieldWithValue("//select[contains(@id, 'us_country')]", player.getCountry());
        clearAndFillFieldWithValue("//textarea[contains(@id, 'us_address')]", player.getAddress());
        clearAndFillFieldWithValue("//input[contains(@id, 'us_phone')]", player.getPhone());
    }

    //takes data from player edit form and creates a new player entity with it, returns player
    private PokerPlayer createPockerPlayerFromForm(){
        WebElement loginField = driver.findElement(By.xpath("//input[contains(@id, 'login')]"));
        WebElement emailField = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
        WebElement fnameField = driver.findElement(By.xpath("//input[contains(@id, 'fname')]"));
        WebElement lnameField = driver.findElement(By.xpath("//input[contains(@id, 'lname')]"));
        WebElement cityField = driver.findElement(By.xpath("//input[contains(@id, 'us_city')]"));
        WebElement countryField;
        try {
            countryField = driver.findElement(By.xpath("//select[contains(@id, 'us_country')]/option[@selected='selected']"));
        } catch(NoSuchElementException e){
            countryField = null;
        }
        WebElement addressField = driver.findElement(By.xpath("//textarea[contains(@id, 'us_address')]"));
        WebElement phoneField = driver.findElement(By.xpath("//input[contains(@id, 'us_phone')]"));

        PokerPlayer player = new PokerPlayer(
                loginField.getAttribute("value"),
                emailField.getAttribute("value"),
                fnameField.getAttribute("value"),
                lnameField.getAttribute("value"),
                cityField.getAttribute("value"),
                countryField == null ? "" : countryField.getText(),
                addressField.getAttribute("value"),
                phoneField.getAttribute("value")
        );

        return player;
    }

    //implements search by username functionality
    private void searchPlayer() throws TestFailedException{
        driver.get(baseUrl + "players");
        //compares titles
        assertResult("PlayerTests", "searchPlayer", "Players", driver.getTitle());

        clearAndFillFieldWithValue("//input[contains(@id, 'login') and not(contains(@id, 'last'))]", player.getUsername()/*"myuser68_6"*/);

        WebElement searchBtn = driver.findElement(By.name("search"));
        searchBtn.click();
    }

    //checks the presence of certain player in the search result table
    private void assertTableContainsPlayer() throws TestFailedException {
        try {
            driver.findElement(By.xpath(".//tr[.//a[text()='"
                    + player.getUsername()/*"myuser68_6"*/ + "']]"));
        } catch (NoSuchElementException e) {
            throw new TestFailedException("PlayerTests", "assertTableContainsPlayer", "Player not found");
        }
    }

    //finds an edit link and opens edit player window
    private void openPlayerEditWindow() throws TestFailedException{
        WebElement editLink = driver.findElement(By.xpath(".//tr[.//a[text()='"
                + player.getUsername() /*"myuser68_6"*/ + "']]//img[@alt='Edit']/.."));

        editLink.click();

        String actualTitle = driver.getTitle();
        String expectedTitle = "Players - Edit";

        //compares titles
        assertResult("PlayerTests", "openPlayerEditWindow", expectedTitle, actualTitle);
    }

}
