package ru.yandex.praktikum;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.praktikum.PageObject.MainPage;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)

public class OrderTest {
    private WebDriver driver;

    @Before
    public void startUp() {
       WebDriverManager.chromedriver().setup();
    }

    @DataProvider
    public static Object[][] orderFormData() {
        return new Object[][]{
                {"Сима", "Симонова", "Ракетный бульвар 5", "Китай-город", "+79999999999", LocalDate.now().plusDays(3), "двое суток", "чёрный жемчуг", "Нужен самокат скорее"},
                {"Артем", "Иванов", "Лубянка 29", "Бульвар Адмирала Ушакова", "+799988881234", LocalDate.now().plusDays(2), "трое суток", "серая безысходность", "ячсм"},
        };
    }

    @Test
    @UseDataProvider("orderFormData")
    public void scooterOrderTest(String name, String lastName, String address, String metro, String phone, LocalDate date, String limitation, String color, String comment) {
        // Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();

        var orderPage = mainPage.orderButtonClick();
        orderPage.fillPersonalInfoOrderForm(name, lastName, address, metro, phone);
        orderPage.nextButtonClick();

        orderPage.fillRentInfoOrderForm(date, limitation, color, comment);
        orderPage.orderButtonClick();

        orderPage.yesButtonClick();

        WebElement orderSuccessDialog = orderPage.getOrderSuccessBlock();
        assertEquals(true, orderSuccessDialog.isDisplayed());
        assertEquals(true, orderSuccessDialog.getText().contains("Заказ оформлен"));
    }

    @After
    public void afterTest() {
        // Закрыть браузер
        driver.quit();
    }
}
