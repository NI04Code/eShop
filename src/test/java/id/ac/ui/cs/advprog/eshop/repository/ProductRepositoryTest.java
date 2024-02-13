package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productRepository.delete(product.getProductId());
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testDeleteProductWithNullId() {
        assertFalse(productRepository.delete(null));
    }

    @Test
    void testDeleteProductNotFound() {
        assertFalse(productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testDeleteMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        assertTrue(productRepository.delete(product2.getProductId()));
        assertTrue(productRepository.delete(product1.getProductId()));
    }

    @Test
    void testEdit() {
        Product productToEdit = new Product();
        productToEdit.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productToEdit.setProductName("Sampo Cap Bambang");
        productToEdit.setProductQuantity(100);
        productRepository.create(productToEdit);

        Product editedProduct = new Product();
        editedProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        editedProduct.setProductName("Sampo Cap Usep");
        editedProduct.setProductQuantity(50);
        // we just change product name and quantity
        productRepository.edit(editedProduct, productToEdit.getProductId());
        assertEquals(editedProduct.getProductName(), productToEdit.getProductName());
        assertEquals(editedProduct.getProductQuantity(), productToEdit.getProductQuantity());
        assertNotEquals(editedProduct.getProductId(), productToEdit.getProductId());
    }

    @Test
    void testEditProductIdNull() {
        Product editedProduct = new Product();
        editedProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        editedProduct.setProductName("Sampo Cap Usep");
        editedProduct.setProductQuantity(50);

        assertThrows(IllegalArgumentException.class, () ->
                productRepository.edit(editedProduct, null));

    }

    @Test
    void testEditProductNotFound() {
        Product editedProduct = new Product();
        editedProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        editedProduct.setProductName("Sampo Cap Usep");
        editedProduct.setProductQuantity(50);

        assertThrows(IllegalArgumentException.class, () ->
                productRepository.edit(editedProduct, "eb558e9f-1c39-460e-8860-71af6af63bd6"));

    }

    @Test
    void testEditMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product editedProduct = new Product();
        product2.setProductId("editedId");
        product2.setProductName("Sampo Cap PAYO");
        product2.setProductQuantity(1000);

        productRepository.edit(editedProduct, product2.getProductId());
        assertEquals(editedProduct.getProductName(), product2.getProductName());
        assertEquals(editedProduct.getProductQuantity(), product2.getProductQuantity());
        assertNotEquals(editedProduct.getProductId(), product2.getProductId());

        productRepository.edit(editedProduct, product1.getProductId());
        assertEquals(editedProduct.getProductName(), product1.getProductName());
        assertEquals(editedProduct.getProductQuantity(), product1.getProductQuantity());
        assertNotEquals(editedProduct.getProductId(), product1.getProductId());
    }
}
