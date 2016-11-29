/**
 * Created by numash on 27.11.2016.
 */
//user exception to locate the problem
public class TestFailedException extends Exception {

    public TestFailedException(){
        super();
    }

    public TestFailedException(String className, String methodName, String reason) {
        super("Failed in class: " + className + " in method: " + methodName + ". \n" + reason);
    }
}
