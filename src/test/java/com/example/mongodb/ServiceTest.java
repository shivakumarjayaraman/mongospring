package com.example.mongodb;

import com.example.mongodb.model.Manufacturer;
import com.example.mongodb.model.Product;
import com.example.mongodb.model.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mongodb.service.ProductService;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class ServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testService() {
        productService.deleteAll();
        Manufacturer acme = new Manufacturer("Acme Corp", "USA");
        Product one = new Product(null, "Large Widget", 19.99, Size.LARGE, acme);
        Product two = new Product(null, "Small Widget", 9.99, Size.SMALL, acme);
        Product three = new Product(null, "Another large Widget", 29.99, Size.LARGE, acme);

        productService.insertProducts(List.of(one, two, three));
        List<Product> products = productService.findProducts(Size.LARGE);
        assertEquals(2, products.size());
        products = productService.findProducts(Size.SMALL);
        assertEquals(1, products.size());
    }
}
