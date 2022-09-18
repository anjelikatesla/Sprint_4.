package ru.yandex.praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Лого "Яндекс самокат"
    private By logoScooter = By.className("Header_LogoScooter__3lsAR");

    // Заголовок "Вопросы о важном"
    private By faqTitle = By.className("Home_SubHeader__zwi_E");

    // Блок Аккардеон
    private By faqAccordion = By.className("accordion");

    // Кнопка в Аккардеоне
    private By faqAccordionButton = By.className("accordion__button");

    // Контейнер ответа по вопросу.
    private By faqElementAnswerByQuestion = By.xpath("../../div[@class='accordion__panel']");

    // Кнопка "да все привыкли"
    private By cookieButton = By.id("rcc-confirm-button");

    // Кнопка "Заказать" в шапке сайта
    private By orderButtonInHead = By.className("Button_Button__ra12g");

    // Кнопка "Заказать" в разделе "Как это работает"
    private By orderButtonInBody = By.cssSelector(".Home_FinishButton__1_cWm .Button_Button__ra12g.Button_Middle__1CSJM");

    public WebElement getElementAccordion(String title) {
        List<WebElement> elements = driver.findElements(faqAccordionButton);
        for (WebElement element : elements) {
            if (element.getText().contains(title)) {
                return element;
            }
        }
        return null;
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public WebElement getAnswerByTitle(WebElement element) {
        return element.findElement(faqElementAnswerByQuestion);
    }

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    /**
     * Выполнить клик по кнопке 'Заказать' в хэдере страницы
     */
    public OrderPage orderButtonClick() {
        driver.findElement(orderButtonInHead).click();
        return new OrderPage(driver);
    }

    /**
     * Выполнить клик по кнопке 'Заказать' в теле страницы
     */
    public OrderPage orderButtonInBodyClick() {
        driver.findElement(orderButtonInBody).click();
        return new OrderPage(driver);
    }

    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
    }
}
