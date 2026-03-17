package com.example.mongodb;

import com.example.mongodb.model.Manufacturer;
import com.example.mongodb.model.Product;
import com.example.mongodb.model.Size;
import com.mongodb.client.internal.MongoDatabaseImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class MongoTemplateTests {

	@Autowired
	private MongoTemplate template;

	@Test
	void templateOperations() {
		System.out.println("Collection names: " + template.getCollectionNames());

		Manufacturer acme = new Manufacturer("Acme Corp", "USA");
		Product bigWidget = new Product(null, "Widget", 19.99, Size.LARGE, acme);
		template.insert(bigWidget);

		Product smallWidget = new Product(null, "Widget", 9.99, Size.SMALL, acme);
		template.insert(smallWidget);
	}


}
