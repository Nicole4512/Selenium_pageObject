package ru.ibs.framework.product;

import ru.ibs.framework.pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    /**
     * Переменная для хранения листа продуктов
     */
    private List<Product> productFeatureList = new ArrayList<Product>();

    private static ProductManager INSTANCE = null;

    private ProductManager(){}

    /**
     * Метод ленивой инициализации ProductManager
     *
     * @return ProductManager
     */
    public static ProductManager getProductManager() {
        if (INSTANCE == null) {
            INSTANCE = new ProductManager();
        }
        return INSTANCE;
    }

    /**
     * Сеттер для productFeatureList
     *
     * @param productFeatureList
     */
    public  void setProductFeatureList(List<Product> productFeatureList) {
        this.productFeatureList = productFeatureList;
        System.out.println(this.productFeatureList.size());
    }


    /**
     * Геттер для productFeatureList
     *
     * @return List<Product>
     */
    public  List<Product> getProductFeatureList() {
        System.out.println("Геттер " + productFeatureList.size());
        return productFeatureList;
    }
}
