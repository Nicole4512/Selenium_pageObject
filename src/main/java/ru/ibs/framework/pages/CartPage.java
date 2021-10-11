package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.product.Product;
import java.util.List;

import static java.lang.Thread.sleep;

public class CartPage extends BasePage {

    @FindBy(xpath = "//a[@class = 'cart-items__product-name-link']")
    private List<WebElement> productNameList;

    @FindBy(xpath = "//div[@class = 'cart-items__product']")
    private List<WebElement> productList;

    @FindBy(xpath = "//div[@class = 'total-amount__content']//span[@class = 'price__current']")
    private WebElement currentPrice;

    @FindBy(xpath = "//div[@class = 'group-tabs-menu']//span[@class = 'restore-last-removed']")
    private WebElement returnItem;

    /**
     * Метод выбора меню товара
     *
     * @param name     - название товара
     * @param menuName - название меню
     *
     * @return CartPage - т.е.остаемня на странице
     */
    public CartPage chooseMenu(String name, String menuName) {
        for (WebElement product : productList) {
            WebElement productName = product.findElement(By.xpath(".//a[@class = 'cart-items__product-name-link']"));
            if (name.equalsIgnoreCase(productName.getText())) {
                List<WebElement> listMenu = product.findElements(By.xpath(".//button[@class = 'menu-control-button']"));
                for (WebElement menu : listMenu) {
                    if (menuName.equalsIgnoreCase(menu.getText())) {
                        menu.click();
                        return this;
                    }
                }
                Assertions.fail("Меню " + menuName + " нет на странице");
            }
        }
        return this;
    }

    /**
     * Метод возвращения в корзину удаленного товара
     *
     * @return CartPage - т.е.остаемня на странице
     */
    public CartPage returnADeleted() {
        waitUtilElementToBeVisible(returnItem);;
        action.moveToElement(returnItem, 1, 2).click().build().perform();
        return this;
    }

    /**
     * Метод изменения количества товара
     *
     * @param name        - название товара
     * @param minusOrPlus - Плюс(мы хотим уветичить количество)/Минус(мы хотим уменьшить количество)
     *
     * @return CartPage - т.е.остаемня на странице
     */
    public CartPage changeQuantity(String name, String minusOrPlus) {
        for (WebElement product : productList) {
            WebElement productName = product.findElement(By.xpath(".//a[@class = 'cart-items__product-name-link']"));

            if (name.equalsIgnoreCase(productName.getText())) {

                WebElement quantity = product.findElement(By.xpath(".//input[@class = 'count-buttons__input']"));
                String value = quantity.getAttribute("value");
                switch (minusOrPlus) {
                    case "Минус":
                        WebElement btnMinus = product.findElement(By.xpath(".//button[@class = 'count-buttons__button count-buttons__button_minus']"));
                        waitUtilElementToBeClickable(btnMinus).click();
                        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(quantity, "value", value)));
                        return this;
                    case "Плюс":
                        WebElement btnPlus = product.findElement(By.xpath(".//button[@class = 'count-buttons__button count-buttons__button_plus']"));
                        waitUtilElementToBeClickable(btnPlus).click();
                        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(quantity, "value", value)));
                        return this;
                    default:
                        Assertions.fail("Меню " + minusOrPlus + " нет на станице");
                }

            }
        }
        Assertions.fail("Продукта " + name + " нет в корзине");
        return this;
    }

    /**
     * Метод проверки удаления товара
     *
     * @param DelName - название удаленного товара
     *
     * @return CartPage - т.е.остаемня на странице
     */
    public CartPage checkDeleted(String DelName) {
        for (WebElement name : productNameList) {
            if (DelName.equalsIgnoreCase(name.getText())) {
                Assertions.fail("Продукт " + DelName + " не был удален");
            }
        }
        return this;
    }

    /**
     * Метод проверки гарантии на товар
     *
     * @param name          - название товара
     * @param guaranteeName - ожидаемое название гарании
     *
     * @return CartPage - т.е.остаемня на странице
     */
    public CartPage checkGuarantee(String name, String guaranteeName) {
        for (WebElement product : productList) {
            WebElement productName = product.findElement(By.xpath(".//a[@class = 'cart-items__product-name-link']"));
            if (productName.getText().equalsIgnoreCase(name)) {
                WebElement guarantee = product.findElement(By.xpath(".//span[@class = 'base-ui-radio-button__icon base-ui-radio-button__icon_checked']"));
                if (!(guaranteeName.equals(guarantee.getText()))) {
                    System.out.println(guarantee.getText());
                    Assertions.fail("Гарантия продукта " + name + " не совпадает с " + guaranteeName);
                }
            }
        }
        return this;
    }

    /**
     * Метод проверки цены за все товары
     *
     * @return CartPage - т.е.остаемня на странице
     */
    public CartPage checkPrice() {
        int price = 0;
        for (Product product : getProductFeatureList()) {
            price += product.getPrice();
        }
        System.out.println(price);
        if (price != parsePrice(currentPrice.getText())) {
            Assertions.fail();
        }
        return this;
    }


}
