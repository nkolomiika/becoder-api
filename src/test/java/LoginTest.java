import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static WebDriver driver;
    private static final String HOST = "http://localhost:5173/";

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLoginSuccess() {
        driver.get(HOST + "login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement registerButton = driver.findElement(By.cssSelector("button"));

        usernameInput.sendKeys("testuser");
        passwordInput.sendKeys("password123");

        registerButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String token = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.getItem('token');");
        assertNotNull(token, "Token should be set in sessionStorage");

        assertEquals(HOST, driver.getCurrentUrl(), "Should redirect to welcome page");
}

    @Test
    public void testLoginFailed() {
        driver.get(HOST + "login");

        ((JavascriptExecutor) driver).executeScript("sessionStorage.removeItem('token');");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement registerButton = driver.findElement(By.cssSelector("button"));

        usernameInput.sendKeys("testuser");
        passwordInput.sendKeys("invalid-password");

        registerButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertDoesNotThrow(() -> {
            WebElement errorMessage = driver.findElement(By.id("error-message"));

            String token = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.getItem('token');");

            assertNull(token, "Token shouldn't be set in sessionStorage");
            assertEquals(HOST + "login", driver.getCurrentUrl(), "Should stay at login page");
        }, "Error message should be shown");

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

