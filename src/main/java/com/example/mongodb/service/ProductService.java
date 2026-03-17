package com.example.mongodb.service;

import com.example.mongodb.model.Product;
import com.example.mongodb.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    MongoOperations mongoOperations;

    public void insertProducts(List<Product> products) {
        mongoOperations.insert(products, Product.class);
    }

    public List<Product> findProducts(Size size) {
        Query query = new Query();
        Criteria criteria = Criteria.where("size");
        query.addCriteria(criteria.is(size));
        List<Product> products = mongoOperations.find(
                query,
                Product.class
        );
        return products;
    }

    public void deleteAll() {
        mongoOperations.dropCollection(Product.class);
    }

}
