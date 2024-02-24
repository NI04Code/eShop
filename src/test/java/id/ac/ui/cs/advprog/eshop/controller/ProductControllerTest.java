package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Pak Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        assertEquals("createProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        String redirectUrl = productController.createProductPost(product, model);

        assertEquals("redirect:/product/list", redirectUrl);
        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        String viewName = productController.productListPage(model);

        assertEquals("productList", viewName);
        verify(productService).findAll();
    }

    @Test
    void testEditProductPage() {
        String viewName = productController.editProductPage("eb558e9f-1c39-460e-8860-71af6af63bd6", model);

        assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPost() {
        Product editedProduct = new Product();
        product.setProductId("le0192");
        product.setProductName("Pak le");
        product.setProductQuantity(69);

        String redirectUrl = productController.editProductPost(editedProduct, model);

        assertEquals("redirect:/product/list", redirectUrl);
    }

    @Test
    void testDeleteProduct() {
        String redirectUrl = productController.deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", model);

        assertEquals("redirect:/product/list", redirectUrl);
        verify(productService).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}
