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

    public Product findById(String id) {
        for (Product product: productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean delete(String productId) {
        return productData.removeIf(product -> product.getProductId().equals(productId));
    }

    public Product edit(Product editedProduct, String productId) {
        Product productToEdit = findById(productId);
        if (productToEdit == null) {
            return null;
        }

        productToEdit.setProductName(editedProduct.getProductName());
        productToEdit.setProductQuantity(editedProduct.getProductQuantity());

        return productToEdit;
    }
}
