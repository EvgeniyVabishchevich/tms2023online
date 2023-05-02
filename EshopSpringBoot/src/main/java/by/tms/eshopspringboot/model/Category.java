package by.tms.eshopspringboot.model;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private int imageId;
    private List<Product> productList;

    public Category(int id, String name, int imageId, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
        this.productList = productList;
    }

    public Category() {
    }

    public Category(String name, int imageId, List<Product> productList) {
        this.name = name;
        this.imageId = imageId;
        this.productList = productList;
    }

    public Category(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

