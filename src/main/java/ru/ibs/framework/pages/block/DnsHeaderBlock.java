package ru.ibs.framework.pages.block;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.CartPage;
import ru.ibs.framework.pages.ListProductPage;
import ru.ibs.framework.pages.ProductPage;
import ru.ibs.framework.product.Product;

import static java.lang.Thread.sleep;


public class DnsHeaderBlock extends BasePage {
    @FindBy(xpath = "//span[@class = 'cart-link__price']")
    private WebElement currentPrice;

    @FindBy(xpath = "//form[contains(@class, 'presearch') and not(contains(@class, 'expand'))]//input[@placeholder =  'Поиск по сайту' and  not(contains(@id, 'null'))]")
    private WebElement finder;

    @FindBy(xpath = "//div[@class= 'buttons']//a[contains(@class, 'cart')]")
    private WebElement menuCart;

    @FindBy(xpath = "//form[contains(@class, 'presearch') and not(contains(@class, 'expand'))]//input[@placeholder =  'Поиск по сайту' and  not(contains(@id, 'null'))]/..//span[@class='ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch']")
    private WebElement findValueBtn;

    @FindBy(xpath = "//a[@class ='btn btn-additional']")
    private WebElement btnPlace;

    /**
     * Метод закрытия окна о городе
     */
    public DnsHeaderBlock closePlaceWindow() {
        waitUtilElementToBeClickable(btnPlace).click();
        return this;
    }

    /**
     * Метод клика на 'Поиск по сайту', ввода значения в окно
     * и клика по кнопке поиска товаров
     *
     * @return ListProductPage - переходим на страничку листа товаром
     */
    public ListProductPage fillFinderList(String value) {
        fillInputField(finder, value);
        findValueBtn.click();
        return pageManager.getListProductPage();
    }

    /**
     * Метод клика на 'Поиск по сайту', ввода значения в окно
     * и клика по кнопке поиска товаров
     *
     * @return ProductPage - переходим на страничку товара
     */
    public ProductPage fillFinderProduct(String value) {
        fillInputField(finder, value);
        waitUtilElementToBeClickable(findValueBtn).click();
        return pageManager.getProductPage();
    }

    /**
     * Метод перехода в корзину с товарами
     *
     * @return CartPage - нужное нам меню, переходим на страницу корзины с товарами
     */

    public CartPage clickCart() {
        waitUtilElementToBeClickable(menuCart);
        menuCart.click();
        return pageManager.getCartPage();
    }

    public DnsHeaderBlock checkPrice() {
        System.out.println(getProductFeatureList().size());
        int price = 0;
        for (Product product : getProductFeatureList()) {
            price += product.getPrice();
            System.out.println("____________________________________________");
            System.out.println(product.getPrice());
        }
        System.out.println(price);
        System.out.println(parsePrice(currentPrice.getText()));
        if (price != parsePrice(currentPrice.getText())) {
            Assertions.fail("Цена корзины не равна сумме покупок");
        }
        return this;
    }

    @Override
    protected Integer parsePrice(String price) {
         return Integer.parseInt(price.replaceAll("\\D", ""));
    }
}
