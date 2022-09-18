package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.PageObject.MainPage;

public class BaseUITest {
    protected static WebDriver driver;
    protected static MainPage mainPage;

    @BeforeClass
    public static void startUp() {
        // Создаём драйвер для браузера Chrome
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();

        // Создаём драйвер для браузера Firefox
         WebDriverManager.firefoxdriver().setup();
         driver = new FirefoxDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @AfterClass
    public static void afterTest() {
        // Закрыть браузер
        driver.quit();
    }
}
