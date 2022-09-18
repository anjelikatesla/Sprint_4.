package ru.yandex.praktikum;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class FAQTest extends BaseUITest  {

    private void compareAnswer(String faqTitle, String expectedFaqAnswer) {
        WebElement element = mainPage.getElementAccordion(faqTitle);
        mainPage.scrollToElement(element);
        WebElement faqAnswer = mainPage.getAnswerByTitle(element);
        assertFalse(faqAnswer.isDisplayed());
        element.click();
        assertTrue(faqAnswer.isDisplayed());
        assertEquals(expectedFaqAnswer, faqAnswer.getText());
        String testResult = String.format("Текст ответа для вопроса \"%s\" успешно проверен.", faqTitle);
        System.out.println(testResult);
    }

    @Test
    public void testQuestionOne() {
        String faqTitle = "Сколько это стоит? И как оплатить?";
        String expectedFaqAnswer = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionTwo() {
        String faqTitle = "Хочу сразу несколько самокатов! Так можно?";
        String expectedFaqAnswer = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionThree() {
        String faqTitle = "Как рассчитывается время аренды?";
        String expectedFaqAnswer = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionFour() {
        String faqTitle = "Можно ли заказать самокат прямо на сегодня?";
        String expectedFaqAnswer = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionFive() {
        String faqTitle = "Можно ли продлить заказ или вернуть самокат раньше?";
        String expectedFaqAnswer = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionSix() {
        String faqTitle = "Вы привозите зарядку вместе с самокатом?";
        String expectedFaqAnswer = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionSeven() {
        String faqTitle = "Можно ли отменить заказ?";
        String expectedFaqAnswer = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

    @Test
    public void testQuestionEight() {
        String faqTitle = "Я жизу за МКАДом, привезёте?";
        String expectedFaqAnswer = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
        compareAnswer(faqTitle, expectedFaqAnswer);
    }

}