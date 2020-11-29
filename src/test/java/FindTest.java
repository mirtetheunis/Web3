import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FindTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mirte\\OneDrive\\Documenten\\Lessen\\Semester 2\\Web2\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Find_DateNotFilledIn_ErrorMessageGiven() {
        driver.get("find.jsp");

        WebElement field=driver.findElement(By.id("hour"));
        field.clear();
        field.sendKeys("18:00");

        WebElement button=driver.findElement(By.id("signUp"));
        button.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Vul alle velden in.", errorMsg.getText());
    }
}
