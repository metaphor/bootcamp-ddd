package io.github.metaphor.council.bootcamp.ddd.support;

import com.github.database.rider.spring.api.DBRider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
@DBRider
@ActiveProfiles("test")
public abstract class FunctionalTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUpRestAssured() {
        RestAssured.port = port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();
    }

    @AfterEach
    public void tearDownRestAssured() {
        RestAssured.reset();
    }
}
