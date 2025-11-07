package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Dashboard {
    WebDriver driver;
    WebDriverWait wait;

    // Locators (update XPaths )
    private By punchInButton = By.xpath("//button[contains(text(),'Punch In') or contains(.,'Punch In')]");
    private By punchOutButton = By.xpath("//button[contains(text(),'Punch Out')]");
    private By toastMessage = By.xpath("//div[contains(@class,'toast') or contains(@class,'notification')]");

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean clickPunchIn() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(punchInButton));
            btn.click();
            System.out.println("✅ Clicked Punch In button.");

            // Wait for toast/confirmation
            wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
            String toast = driver.findElement(toastMessage).getText();
            System.out.println("🔔 Toast message: " + toast);

            return toast.toLowerCase().contains("success") || toast.toLowerCase().contains("punched in");
        } catch (Exception e) {
            System.out.println("⚠️ Punch In action failed: " + e.getMessage());
            return false;
        }
    }

    public boolean clickPunchOut() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(punchOutButton));
            btn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
            String toast = driver.findElement(toastMessage).getText();
            System.out.println("🔔 Toast message: " + toast);
            return toast.toLowerCase().contains("success") || toast.toLowerCase().contains("punched out");
        } catch (Exception e) {
            System.out.println("⚠️ Punch Out action failed: " + e.getMessage());
            return false;
        }
    }
}
