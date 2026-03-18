package com.example.mongodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;

@Component
@AccessType(AccessType.Type.PROPERTY)
public class MyComponent {
    private String value;

    public String getValue() {
        return value;
    }

    @Value("${my.component.value}")
    public void setValue(String value) {
        this.value = value;
    }
}
