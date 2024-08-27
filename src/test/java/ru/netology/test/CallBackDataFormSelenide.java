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
