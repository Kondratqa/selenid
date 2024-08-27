package ru.netology.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallBackDataForm {
    private WebDriver driver;
    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        
    }
    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }
    @Test
    void shouldTestCallback() throws InterruptedException {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id=city] input")).sendKeys("Москва");
        driver.findElement(By.cssSelector("[data-test-id=date] input")).sendKeys("28.05.2023");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79281234567");

        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("span[class=button__text]")).click();
        Thread.sleep(10000);

        WebElement successMessageElement = driver.findElement(By.cssSelector("[data-test-id=notification]"));
        assertTrue(successMessageElement.isDisplayed(), "Сообщение об успешной отправке должно быть видимым.");

        String text = driver.findElement(By.cssSelector("[data-test-id=notification]")).getText();
        System.out.println(text);
    }

}
