package com.example.mongodb;

import com.example.mongodb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CleanupTest {
    @Autowired
    ProductService productService;

    @Test
    public void cleanup() {
        productService.deleteAll();
    }
}
