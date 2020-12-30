import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//Thao De Clercq - Mirte Theunis

public class LogInPage extends Page{

    @FindBy(id="userId")  // verander
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="logIn")
    private WebElement logInButton;


    public LogInPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Home");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submit() {
        logInButton.click();
    }


    public boolean hasErrorMessage (String message) {
        List<WebElement> errorMsg = driver.findElements(By.cssSelector("div.alert-danger ul li"));
        for (WebElement webElement : errorMsg) {
            if (webElement.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSuccessMessage (String message) {
        WebElement successMsg = driver.findElement(By.cssSelector("div.alert-feedback p"));
        return message.equals(successMsg.getText());
    }

    public boolean hasJavaScriptMessageUserid (String message) {
        WebElement jsMsg = driver.findElement(By.id("error-for-userId"));
        return message.equals(jsMsg.getText());
    }

    public boolean hasJavaScriptMessagePassword (String message) {
        WebElement jsMsg = driver.findElement(By.id("error-for-password"));
        return message.equals(jsMsg.getText());
    }

    public void logIn(String userid, String password) {
        useridField.sendKeys(userid);
        passwordField.sendKeys(password);
        logInButton.click();
    }

}
