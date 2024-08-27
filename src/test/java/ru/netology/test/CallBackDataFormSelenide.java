package ru.netology.test;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallBackDataFormSelenide {
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
        open("http://localhost:9999");

        $$(".input__control").findBy(attribute("placeholder", "Город")).sendKeys("Москва");
        $$(".input__control").findBy(attribute("pattern", "[0-9.]*")).sendKeys("28.05.2023");
        $$(".input__control").findBy(attribute("name", "name")).sendKeys("Иванов Иван");
        $$(".input__control").findBy(attribute("name", "phone")).sendKeys("+79281234567");
        $(".checkbox__box").click();
        $("span[class=button__text]").click();
        Thread.sleep(10000);
        SelenideElement successMessageElement = $("[data-test-id=notification]");
        successMessageElement.shouldBe(visible);

        String text = successMessageElement.getText();
        System.out.println(text);
    }

}
