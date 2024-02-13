package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreate() {
        Product product = new Product();
        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
    }

    @Test
    public void testFindAll() {
        List<Product> allProduct = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        allProduct.add(product1);
        allProduct.add(product2);

        when(productRepository.findAll()).thenReturn(allProduct.iterator());

        List<Product> productList = productService.findAll();
        assertEquals(2, allProduct.size());
    }

    @Test
    public void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Pak Bambang");
        product.setProductQuantity(5);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.delete(product.getProductId()))
                .thenReturn(productList.removeIf(productInList ->
                        productInList.getProductId().equals(product.getProductId())));

        boolean validDeleteId = productService.delete(product.getProductId());
        assertEquals(0, productList.size());
        assertTrue(validDeleteId);
    }

    @Test
    public void testDeleteInvalidId() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Pak Bambang");
        product.setProductQuantity(5);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.delete("invalid id"))
                .thenReturn(productList.removeIf(productInList ->
                        productInList.getProductId().equals("invalid id")));

        boolean invalidDeleteId = productService.delete("invalid id");
        assertFalse(invalidDeleteId);
        assertEquals(1, productList.size());
    }

    @Test
    public void testEdit() {
        Product productToEdit = new Product();
        productToEdit.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productToEdit.setProductName("Pak Bambang");
        productToEdit.setProductQuantity(5);

        List<Product> productList = new ArrayList<>();
        productList.add(productToEdit);

        Product editedProduct = new Product();
        editedProduct.setProductName("Pak Le papa le");
        editedProduct.setProductQuantity(69);

        when(productRepository.edit(any(Product.class), any(String.class)))
                .thenAnswer(invocation -> {
                    String productId = invocation.getArgument(1);
                    Product productToEditInRepo = productList.stream().filter(product -> product.getProductId().equals(productId)).findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

                    Product editedProductInRepo = invocation.getArgument(0);
                    productToEditInRepo.setProductName(editedProductInRepo.getProductName());
                    productToEditInRepo.setProductQuantity(editedProductInRepo.getProductQuantity());

                    return productToEditInRepo;
                });

        productService.edit(editedProduct, productToEdit.getProductId());

        assertEquals(editedProduct.getProductName(), productToEdit.getProductName());
        assertEquals(editedProduct.getProductQuantity(), productToEdit.getProductQuantity());
        // even though the name and quantity same, but Id remain different
        assertNotEquals(editedProduct.getProductId(), productToEdit.getProductId());
    }
}
