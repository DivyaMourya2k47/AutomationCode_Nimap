package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    WebDriver driver;
    WebDriverWait wait;

    
    private By usernameField = By.xpath("//input[contains(@formcontrolname,'email') or contains(@placeholder,'Email')]");
    private By passwordField = By.xpath("//input[contains(@type,'password') or contains(@formcontrolname,'password')]");
    private By loginButton = By.xpath("//button[@id='kt_login_signin_submit' or .//span[contains(text(),'Sign In')]]");

    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String username, String password) {
        try {
            WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            user.clear();
            user.sendKeys(username);

            WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            pass.clear();
            pass.sendKeys(password);

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            btn.click();

        } catch (Exception e) {
            System.out.println("❌ Login elements not found or page did not load properly.");
        }
    }

    public boolean isLoginSuccessful() {
        try {
            // wait for redirect or dashboard element
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("dashboard"),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Dashboard')]"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
