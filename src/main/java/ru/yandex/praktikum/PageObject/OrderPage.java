package ru.yandex.praktikum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderPage {
    private WebDriver driver;

    // Кнопка "Посмотреть статус"
    private By statusButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Посмотреть статус']");

    // Поле для ввода имени
    private By firstNameInput = By.cssSelector("input[placeholder=\"* Имя\"]");

    // Поле для ввода фамилии
    private By lastNameInput = By.cssSelector("input[placeholder=\"* Фамилия\"]");

    // Поле для ввода адреса
    private By adressInput = By.cssSelector("input[placeholder=\"* Адрес: куда привезти заказ\"]");

    // Станция метро по поиску
    private By metroStantionValue = By.className("select-search__value");

    // Поле для ввода станции метро
    private By metroStantionInput = By.className("select-search__input");

    // Выбор станции метро
    private By metroStantionSelect = By.className("select-search__select");


    // Поле для ввода телефона
    private By phoneNumberInput = By.cssSelector("input[placeholder=\"* Телефон: на него позвонит курьер\"]");

    // Кнопка "Далее"
    private By furtherButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Далее']");

    // Поле "Когда привезти самокат"
    private By dateOrderInput = By.cssSelector("input[placeholder=\"* Когда привезти самокат\"]");

    // Поле "Скрок аренды"
    private By rentalPeriodBlock = By.className("Dropdown-placeholder");

    // Выпадаюсщий список срока аренды
    private By rentalPeriodOption = By.cssSelector(".Dropdown-menu .Dropdown-option");


    // Блок "Цвет самоката"
    private By colorScooterLabel = By.cssSelector("label.Checkbox_Label__3wxSf");

    // Поле "Комментарий бдля курьера"
    private By commentForDelivery = By.cssSelector("input[placeholder=\"Комментарий для курьера\"]");

    // Кнопка "Заказать"
    private By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Заказать']");


    // Кнопка "Да"
    private By yesButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Да']");

    // Окно статуса заказа
    private By formOrderInfo = By.className("Order_Modal__YZ-d3");

    // Успешный статус заказа
    private By successOrderStatus = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setMetroOptionValue(String option) {
        driver.findElement(metroStantionValue).click();
        driver.findElement(metroStantionInput).sendKeys(option);
        driver.findElement(metroStantionSelect).click();
    }

    /**
     * Первая часть заказа.
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param metroStation
     * @param phoneNumber
     */
    public void fillPersonalInfoOrderForm(String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(adressInput).sendKeys(address);
        setMetroOptionValue(metroStation);
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void nextButtonClick() {
        driver.findElement(furtherButton).click();
    }

    /**
     * Вторая часть заказа.
     *
     * @param date
     * @param rentLimitation
     * @param color
     * @param comment
     */
    public void fillRentInfoOrderForm(LocalDate date, String rentLimitation, String color, String comment) {
        var dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        driver.findElement(dateOrderInput).sendKeys(date.format(dateFormatter));
        driver.findElement(dateOrderInput).sendKeys(Keys.ENTER);
        chooseRentalPeriodOption(rentLimitation);
        selectScooterColorByName(color);
        driver.findElement(commentForDelivery).sendKeys(comment);
    }

    public void orderButtonClick() {
        driver.findElement(orderButton).click();
    }

    public void yesButtonClick() {
        driver.findElement(yesButton).click();
    }

    private void selectScooterColorByName(String color) {
        List<WebElement> colorLabels = driver.findElements(colorScooterLabel);
        for (WebElement colorLabel : colorLabels) {
            if (colorLabel.getText().equalsIgnoreCase(color)) {
                colorLabel.click();
            }
        }
    }

    private void chooseRentalPeriodOption(String rentLimitation) {
        driver.findElement(rentalPeriodBlock).click();
        List<WebElement> options = driver.findElements(rentalPeriodOption);
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(rentLimitation)) {
                option.click();
                return;
            }
        }
    }

    public WebElement getOrderSuccessBlock() {
        return driver.findElement(successOrderStatus);
    }

    public void statusButtonClick() {
        driver.findElement(statusButton).click();
    }
}
