package com.github.fernandoteixxeira.sqslistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fernandoteixxeira.sqslistener.entity.Person;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"cloud.aws.endpoint=http://localhost:4566"})
@ActiveProfiles("local")
public class PersonControllerTest {

    @Value("${local.server.port}")
    private int port;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("cloud.aws.sqs.endpoint", () -> "http://localhost:4566/000000000000/person");
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testPersonController() throws Exception {
        Person person = new Person("1", "John Doe", LocalDate.parse("2000-01-01"));

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        String jsonPayload = objectMapper.writeValueAsString(person);

        given()
                .contentType("application/json")
                .body(jsonPayload)
                .when()
                .post("/person")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
