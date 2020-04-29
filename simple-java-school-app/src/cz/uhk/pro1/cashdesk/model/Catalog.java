package cz.uhk.pro1.cashdesk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

}
