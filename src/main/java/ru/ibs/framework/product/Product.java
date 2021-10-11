package ru.ibs.framework.product;


public class Product {
    private String name;

    private int price;

    private String guarantee;


    public Product(String name, int price, String guarantee) {
        this.name = name;
        this.price = price;
        this.guarantee = guarantee;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
