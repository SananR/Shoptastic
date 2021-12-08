package me.shoptastic.app.data.model;

public class Product {

    private final String name;
    private final Integer id;
    private Float price;
    private String description;
    private final String storeName;


    public Product(String name, String description, Float Price, Integer Id, String storeName) {
        this.name = name;
        this.description = description;
        this.id = Id;
        this.price = Price;
        this.storeName = storeName;
    }

    public Product() {
        this.storeName = "";
        this.id = 0;
        this.name = "";
    }

    public Integer getId(){
        return this.id;
    }

    public Float getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void changePrice(Float Price) {
        this.price = Price;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_name='" + name + '\'' +
                ", Id=" + id +
                ", Price=" + price +
                ", description='" + description + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}

