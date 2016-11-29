import org.openqa.selenium.WebDriver;

/**
 * Created by numash on 27.11.2016.
 */
//parent class of LoginTests and PlayerTests classes
public abstract class BaseTests {

    protected WebDriver driver;
    protected String baseUrl;

    protected BaseTests(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    //throws an exception when actual result not equals to expected result
    protected void assertResult(String className, String methodName, Object expected, Object actual) throws TestFailedException{
        if(!actual.equals(expected)) {
            String reason = "\tExpected result: " + expected + ",\n\tActual result: " + actual;
            throw new TestFailedException(className, methodName, reason);
        }
    }

    //prints in logs an error when actual result not equals to expected result
    protected void verifyResult(String className, String methodName, Object expected, Object actual){
        if(!actual.equals(expected)) {
            System.err.println("Failed in class: " + className + " in method: " + methodName + ". "
                    + "\n\tExpected result: " + expected + ", \n\tActual result: " + actual);
        }
    }

    //runs a test
    public abstract void run() throws TestFailedException;
}
