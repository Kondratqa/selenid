package ru.netology.test;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CallBackDataFormSelenide {
    @Test
    void shouldTestCallback() throws InterruptedException {
        open("http://localhost:9999");

        $("[data-test-id=city] input").sendKeys("Москва");
        $("[data-test-id=date] input").sendKeys("28.05.2025");
        $("[data-test-id=name] input").sendKeys("Москва");
        $("[data-test-id=phone] input").sendKeys("+79000000000");
        $(".checkbox__box").click();
        $("span[class=button__text]").click();
        $("[data-test-id=notification]");

    }

}
