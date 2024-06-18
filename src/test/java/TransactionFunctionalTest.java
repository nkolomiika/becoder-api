import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TransactionFunctionalTest {
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
    public void testTransactionSuccess() {
        driver.get(HOST + "login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement registerButton = driver.findElement(By.cssSelector("button"));

        usernameInput.sendKeys("testuser");
        passwordInput.sendKeys("password123");

        registerButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement transactionButton = driver.findElements(By.id("transaction-button")).get(0);
        transactionButton.click();

        WebElement sumInput = driver.findElement(By.name("sum"));
        WebElement sellerIdInput = driver.findElement(By.name("sellerId"));
        WebElement transactionConfirm = driver.findElement(By.id("transaction-confirm-button"));

        sumInput.sendKeys("1000");
        sellerIdInput.sendKeys("aEjMQ96mISnR5OaGuJACrQAUb90dEdgL");

        transactionConfirm.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

