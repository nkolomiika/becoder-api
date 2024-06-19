import org.junit.jupiter.api.*;
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

    @BeforeEach
    public void loginBefore() {
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
    }

    @Test
    public void testTransactionSuccess() {
        WebElement transactionButton = driver.findElements(By.id("transaction-button")).get(0);
        transactionButton.click();

        WebElement sumInput = driver.findElement(By.name("sum"));
        WebElement sellerIdInput = driver.findElement(By.name("sellerId"));
        WebElement accountBalance = driver.findElement(By.id("account-balance"));
        long balance = Long.parseLong(accountBalance.getText().substring(1));
        WebElement transactionConfirm = driver.findElement(By.id("transaction-confirm-button"));

        String sellerId = "aEjMQ96mISnR5OaGuJACrQAUb90dEdgL";

        sumInput.sendKeys("1000");
        sellerIdInput.sendKeys(sellerId);

        transactionConfirm.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Alert alt = driver.switchTo().alert();
        alt.accept();

        driver.navigate().refresh();
        WebElement accountBalanceAfter = driver.findElement(By.id("account-balance"));
        long balanceAfter = Long.parseLong(accountBalanceAfter.getText().substring(1));
        Assertions.assertEquals(balance - 1000, balanceAfter, "Balance should be reduced by 1000");

        WebElement lastTransaction = driver.findElements(By.className("transaction-seller-id")).get(0);
        Assertions.assertEquals(lastTransaction.getText(), sellerId, "Last transaction sellerId check");
    }

    @Test
    public void testTransactionNotEnoughFail() {
        WebElement transactionButton = driver.findElements(By.id("transaction-button")).get(0);
        transactionButton.click();

        WebElement sumInput = driver.findElement(By.name("sum"));
        WebElement sellerIdInput = driver.findElement(By.name("sellerId"));
        WebElement accountBalance = driver.findElement(By.id("account-balance"));
        long balance = Long.parseLong(accountBalance.getText().substring(1));
        WebElement transactionConfirm = driver.findElement(By.id("transaction-confirm-button"));

        String sellerId = "aEjMQ96mISnR5OaGuJACrQAUb90dEdgL";

        sumInput.sendKeys("1000000");
        sellerIdInput.sendKeys(sellerId);

        transactionConfirm.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Alert alt = driver.switchTo().alert();
        alt.accept();

        driver.navigate().refresh();
        WebElement accountBalanceAfter = driver.findElement(By.id("account-balance"));
        long balanceAfter = Long.parseLong(accountBalanceAfter.getText().substring(1));

        Assertions.assertEquals(balance, balanceAfter, "Balances before and after should be equal");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

