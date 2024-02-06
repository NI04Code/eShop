package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public String delete(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
        return productId;
    }

    public Product edit(String productId) {
        // return product that want to be edited by it ID
        return productData.stream().filter(product -> product.getProductId().equals(productId)).findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
    }
}
