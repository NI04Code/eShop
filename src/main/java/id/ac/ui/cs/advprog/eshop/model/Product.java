package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    private static long id = 0;

    public Product() {
        this.productId = generateHexId();
    }

    public String generateHexId() {
        id++;
        return Long.toHexString(id);
    }

}
