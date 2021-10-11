package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[@class = 'product-card-top__title']")
    private WebElement productName;

    @FindBy(xpath = "//div[@class ='product-buy__price product-buy__price_active']")
    private WebElement priceSale;

    @FindBy(xpath = "//div[@class = 'product-buy__price']")
    private WebElement priceWithoutSale;

    @FindBy(xpath = "//div[@class = 'product-buy product-buy_one-line']//button[contains(@class,'buy-btn')]")
    private WebElement buyBtn;

    @FindBy(xpath = "//div[contains(@class, 'additional-sales-tabs__title') and not(contains(@class, 'wrap'))]")
    private List<WebElement> additionalTitlesWrap;

    @FindBy(xpath = "//div[@class = 'product-card-top__buy']//div[contains(text(), 'цена изменена')]")
    private WebElement changeText;

    @FindBy(xpath = "//span[@class = 'ui-radio__content']//span[@class = 'product-warranty__period']")
    private List<WebElement> guaranteeList;

    List<Product> list = new ArrayList<Product>();


    /**
     * Метод поиска подменю
     *
     * @param menuName - название меню
     * @return ProductPage -  т.е. остаемся на этой странице
     */
    public ProductPage chooseMenu(String menuName) {
        for (WebElement menu : additionalTitlesWrap) {
            if (menuName.contains(menu.getText())) {
                menu.click();
            }
        }
        return this;
    }

    /**
     * Метод выбора гарантии на товар
     *
     * @param nameGuarantee - название гарантии
     * @return ProductPage -  т.е. остаемся на этой странице
     */
    public ProductPage ChooseGuarantee(String nameGuarantee) {
        for (WebElement guarantee : guaranteeList) {
            if (nameGuarantee.equalsIgnoreCase(guarantee.getText())) {
                waitUtilElementToBeClickable(guarantee).click();
            }
        }
        return this;
    }

    /**
     * Метод клина по кнопке "Купить"
     *
     * @return ProductPage -  т.е. остаемся на этой странице
     */
    public ProductPage clickBuy (){
        waitUtilElementToBeClickable(buyBtn);
        buyBtn.click();
        return this;
    }

    public ProductPage getProduct(String sale){
        WebElement price = priceSale;
        switch (sale) {
            case "Без скидки" -> price = priceWithoutSale;
            case "Со скидкой" -> price = priceSale;
            default -> Assertions.fail("Введен неправильный параметр sale");
        }
        list.add(new Product(parseName(productName.getText()), parsePrice(price.getText()), parseGuarantee()));
        System.out.println(list.size());
        setProductFeatureList(list);
        return this;
    }

    private String parseGuarantee(){
        String guaranteeStatus = "Нет гарантии";
        for (WebElement menu : additionalTitlesWrap){
            if(menu.getText().contains("Гарантия")){
                guaranteeStatus = menu.getText();
            }
        }
        return guaranteeStatus;
    }


    private String parseName(String name){
        return name.replaceAll("\n", "");
    }

    public ProductPage changePrice(String productName){
        waitUtilElementToBeVisible(changeText);
        for(Product product : getProductFeatureList()){
            if(product.getName().equals(productName)){
                product.setPrice(parsePrice(priceSale.getText()));
            }
        }
        return this;
    }

}
