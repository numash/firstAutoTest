import java.util.Random;

/**
 * Created by numash on 25.11.2016.
 */
public class RandomManager {
    private Random randomNumber;
    private String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";

    public RandomManager() {
        randomNumber = new Random();
    }

    public String getRandomString(int length) {
        String randomCharacter = "";
        for (int i = 0; i < length; i++) {
            randomCharacter += alphabet.charAt(randomNumber.nextInt(alphabet.length()));
        }
        return randomCharacter;
    }

}
