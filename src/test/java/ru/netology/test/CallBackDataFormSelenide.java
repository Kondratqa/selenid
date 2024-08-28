package ru.netology.test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CallBackDataFormSelenide {
        @Test
        void shouldTestCallback() {
            open("http://localhost:9999");

            $("[data-test-id=city] input").sendKeys("Москва");

            LocalDate localDate = LocalDate.now().plusDays(3);
            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDate);

            $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
            $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
            $("[data-test-id=date] input").sendKeys( date);
            $("[data-test-id=name] input").sendKeys("Москва");
            $("[data-test-id=phone] input").sendKeys("+79000000000");
            $(".checkbox__box").click();
            $("span[class=button__text]").click();

            SelenideElement successMessageElement = $("[data-test-id=notification]");
            successMessageElement.shouldBe(Condition.visible, Duration.ofSeconds(20));
            successMessageElement.shouldHave(text("Встреча успешно забронирована на " + date));
        }
}
