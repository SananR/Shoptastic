package me.shoptastic.app.data.model;

public class Product {

    private final String product_name;
    private final Integer Id;
    private Float Price;
    private String description;
    private final String storeName;


    public Product(String name, String description, Float Price, Integer Id, String storeName) {
        product_name = name;
        this.description = description;
        this.Id = Id;
        this.Price = Price;
        this.storeName = storeName;
    }

    public Integer getId(){
        return this.Id;
    }

    public Float getPrice() {
        return this.Price;
    }

    public String getName() {
        return this.product_name;
    }

    public String getDescription() {
        return this.description;
    }

    public void changePrice(Float Price) {
        this.Price = Price;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Id.equals(product.Id);
    }

    @Override
    public int hashCode() {
        return Id;
    }

    public String getStoreName() {
        return storeName;
    }
}

