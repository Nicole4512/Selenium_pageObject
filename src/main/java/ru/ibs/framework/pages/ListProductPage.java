package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListProductPage extends BasePage{
    @FindBy(xpath = "//a[@class = 'catalog-product__name ui-link ui-link_black']//span")
    private List<WebElement> productList;
// 6.1" Смартфон Apple iPhone Xr 64 ГБ желтый [6x2.5 ГГц, 3 Гб, 1 SIM, IPS, 1792x828, камера 12 Мп, NFC, 4G, GPS, 2942 мА*ч]

    /**
     * Метод поиска товара и клика по нему
     * @param productName - полное название продукта
     */
    public ProductPage findProduct(String productName){
        waitUtilElementToBeVisible(productList.get(0));
        for (WebElement product : productList){
            if(productName.equalsIgnoreCase(product.getText())){
                product.click();
                return pageManager.getProductPage();
            }
        }
        Assertions.fail("Продукта " + productName + " нет на странице");
        return pageManager.getProductPage();
    }



}
