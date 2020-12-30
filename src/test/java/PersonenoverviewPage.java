import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Baljit Singh & Mathias Devos
 */

public class PersonenoverviewPage extends Page {

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="firstName")
    private WebElement firstname;

    @FindBy(id="lastName")
    private WebElement lastname;

    public PersonenoverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Overview");
    }

    public boolean containsUserWithEmail(String email) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                found=true;
            }
        }
        return found;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getFirstname() {
        return firstname;
    }

    public WebElement getLastname() {
        return lastname;
    }

    public boolean hasErrorMessage (String message) {
        List<WebElement> errorMsg = driver.findElements(By.cssSelector("p.alert-danger"));
        for (WebElement webElement : errorMsg) {
            if (webElement.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }
}