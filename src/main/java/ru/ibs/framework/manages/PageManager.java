package ru.ibs.framework.manages;

import ru.ibs.framework.pages.CartPage;
import ru.ibs.framework.pages.ListProductPage;
import ru.ibs.framework.pages.ProductPage;
import ru.ibs.framework.pages.StartPage;


public class PageManager {
    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private static StartPage startPage;

    /**
     * Страничка с листом продуктов
     */
    private static ListProductPage listProductPage;

    /**
     * Страничка корзины
     */
    private static CartPage cartPage;

    /**
     * Страничка продукта
     */
    private static ProductPage productPage;


    /**
     *  privat конструктор PageManager (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Метод ленивой инициализации PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Метод ленивой инициализации StartPage
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Метод ленивой инициализации ListProductPage
     *
     * @return ListProductPage
     */
    public ListProductPage getListProductPage() {
        if (listProductPage == null) {
            listProductPage = new ListProductPage();
        }
        return listProductPage;
    }

    /**
     * Метод ленивой инициализации CartPage
     *
     * @return CartPage
     */
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }


    /**
     * Метод ленивой инициализации ProductPage
     *
     * @return ProductPage
     */
    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }


}
