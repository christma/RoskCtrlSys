package org.example.api;


import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApiTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest {
    @DisplayName("测试Hello2")
    @Test
    @Order(2)
    public void testHello2() {
        System.out.println("hello2");
    }

    @DisplayName("测试Hello1")
    @Test
    @Order(1)
    public void testHello1() {
        System.out.println("hello1");
    }
}
