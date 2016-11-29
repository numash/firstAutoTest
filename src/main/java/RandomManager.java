import java.util.Random;

/**
 * Created by numash on 25.11.2016.
 */
//class that manage random data
public class RandomManager {
    private Random randomNumber;
    private String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";

    public RandomManager() {
        randomNumber = new Random();
    }

    //generates random string of a certain length
    public String getRandomString(int length) {
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += alphabet.charAt(randomNumber.nextInt(alphabet.length()));
        }
        return randomString;
    }

}
