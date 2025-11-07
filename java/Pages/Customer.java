package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Customer {
    WebDriver driver;
    WebDriverWait wait;

    // Locators (adjust)
    private By addCustomerButton = By.xpath("//button[contains(text(),'Add Customer')]");
    private By nameField = By.xpath("//input[@placeholder='Customer Name' or @name='name']");
    private By emailField = By.xpath("//input[@placeholder='Email' or @name='email']");
    private By phoneField = By.xpath("//input[@placeholder='Phone' or @name='phone']");
    private By submitButton = By.xpath("//button[contains(text(),'Save') or contains(text(),'Submit')]");
    private By toastMessage = By.xpath("//div[contains(@class,'toast') or contains(@class,'notification')]");

    public Customer(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean addCustomer(String name, String email, String phone) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addCustomerButton)).click();
            System.out.println("🟢 Opened Add Customer form.");

            wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
            driver.findElement(emailField).sendKeys(email);
            driver.findElement(phoneField).sendKeys(phone);
            System.out.println("📝 Entered customer details.");

            driver.findElement(submitButton).click();
            System.out.println("📤 Submitted Add Customer form.");

            wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
            String toast = driver.findElement(toastMessage).getText();
            System.out.println("🔔 Toast message: " + toast);

            return toast.toLowerCase().contains("success") || toast.toLowerCase().contains("added");
        } catch (Exception e) {
            System.out.println("⚠️ Add Customer action failed: " + e.getMessage());
            return false;
        }
    }
}
