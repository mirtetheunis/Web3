import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class PersonenoverviewTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mirte\\OneDrive\\Documenten\\Lessen\\Semester 3\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void Test_Logged_In_As_Admin_Shows_OverviewPage() {
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        logInPage.logIn("Admin", "admin");

        PersonenoverviewPage personOverviewPage = PageFactory.initElements(driver, PersonenoverviewPage.class);
        //assertEquals("Overview", personOverviewPage.getTitle());
        assertEquals("Overview", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.containsUserWithEmail("admin@dcoptimi"));
    }

    @Test
    public void Test_Logged_Out_User_Cannot_See_OverviewPage() {
        //geeft als return de homePage aangezien we als een bezoeker geen rechten hebben om deze pagina te bekijken
        PersonenoverviewPage personOverviewPage = PageFactory.initElements(driver, PersonenoverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.hasErrorMessage("You have insufficient rights to have a look at this page."));
    }

    /*@After
    public void clean() {
        driver.quit();
    }*/
}
