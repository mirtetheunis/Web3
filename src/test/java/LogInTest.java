import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//Thao De Clercq - Mirte Theunis

public class LogInTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/web3_project_mirtetheunis_war_exploded/Controller"; //VERANDER

    @Before
    public void setUp() {
        //VERANDER
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mirte\\OneDrive\\Documenten\\Lessen\\Semester 3\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_LogIn_AllFieldsFilledInCorrectly_UserIsLoggedIn() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("1a"); //VERANDER
        logInPage.setPassword("mirte"); //VERANDER

        logInPage.submit();

        assertEquals("Home", logInPage.getTitle()); //VERANDER
        assertTrue(logInPage.hasSuccessMessage("Successfully logged in.")); //VERANDER

    }

    @Test
    public void test_LogIn_UserIdNotFilledIn_ErrorMesssageGiven() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("");
        logInPage.setPassword("mirte"); //VERANDER

        assertEquals("Home", logInPage.getTitle());
        assertTrue(logInPage.hasJavaScriptMessageUserid("Please fill out a value")); // VERANDER
    }

    @Test
    public void test_LogIn_PasswordNotFilledIn_ErrorMessageGiven() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setPassword("");
        logInPage.setUserid("1a"); //VERANDER

        assertEquals("Home", logInPage.getTitle());
        assertTrue(logInPage.hasJavaScriptMessagePassword("Please fill out a value")); // VERANDER
    }

    @Test
    public void test_LogIn_UserIdFilledIn_PasswordFilledIn_WrongCombination_ErrorMessageGiven() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.setUserid("1a");
        logInPage.setPassword("hallo"); //VERANDER

        logInPage.submit();

        assertEquals("Home", logInPage.getTitle());
        assertTrue(logInPage.hasErrorMessage("Password is not correct.")); // VERANDER
    }
    //Juiste userid met fout ww -> krijg error message
    //Fout userid met -> error
    //Beide juist -> ingelogd , "welkom..."

}