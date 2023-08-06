package com.example.crud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {
    
    @Test
    void testAplicationContext() {

        ApplicationContext context = 
            new AnnotationConfigApplicationContext(CrudConfiguration.class);

        Assertions.assertNotNull(context);
    }
}
