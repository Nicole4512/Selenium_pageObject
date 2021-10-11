package ru.ibs.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.manages.DriverManager;
import ru.ibs.framework.manages.PageManager;
import ru.ibs.framework.manages.TestPropManager;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.pages.block.DnsHeaderBlock;
import ru.ibs.framework.product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс всех Page
 */
public class BasePage {

    /**
     * Переменная для хранения листа продуктов
     */
    private List<Product> productFeatureList = new ArrayList<Product>();

    /**
     * Баазовая "шапка" всех страничек
     *
     * @see DnsHeaderBlock
     */
    private DnsHeaderBlock dnsHeaderBlock;

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    protected final DriverManager driverManager = DriverManager.getDriverManager();


    /**
     * Менеджер страничек
     *
     * @see PageManager
     */
    protected PageManager pageManager = PageManager.getPageManager();


    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @see Actions
     */
    protected Actions action = new Actions(driverManager.getWebDriver());


    /**
     * Объект для выполнения любого js кода
     *
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getWebDriver();


    /**
     * Объект явного ожидания
     * При применении будет ожидать заданного состояния 10 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(driverManager.getWebDriver(), 10, 1000);


    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    protected final TestPropManager props = TestPropManager.getTestPropManager();


    /**
     * Конструктор позволяющий инициализировать все странички и их элементы помеченные аннотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link org.openqa.selenium.support.PageFactory}
     *
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver driver, Object page)
     */
    public BasePage() {
        PageFactory.initElements(driverManager.getWebDriver(), this);
    }


    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    /**
     * Явное ожидание состояния clickable элемента
     *
     * @param element - веб-элемент который требует проверки clickable
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-элемент поле ввода
     * @param value - значение вводимое в поле
     */
    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        field.click();
        field.clear();
        field.sendKeys(value);
    }

    /**
     * Геттер для DnsHeaderBlock
     * @return DnsHeaderBlock
     */
    public DnsHeaderBlock getDnsHeaderBlock() {
        if (dnsHeaderBlock == null) {
            dnsHeaderBlock = new DnsHeaderBlock();
        }
        return dnsHeaderBlock;
    }

    public void setProductFeatureList(List<Product> productFeatureList) {
        this.productFeatureList = productFeatureList;
    }


    /**
     * Геттер для productFeatureList
     * @return List<Product>
     */
    public List<Product> getProductFeatureList() {
        return productFeatureList;
    }

    protected Integer parsePrice(String price){
        price = price.substring(0 ,price.indexOf('₽'));
        return Integer.parseInt(price.replaceAll("\\D", ""));
    }
}