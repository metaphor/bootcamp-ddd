package io.github.metaphor.council.bootcamp.ddd.ft.customer;


import com.github.database.rider.core.api.dataset.DataSet;
import io.github.metaphor.council.bootcamp.ddd.support.FunctionalTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class CustomerFunctionalTest extends FunctionalTest {


    @Test
    @DataSet("datasets/customer_single.yaml")
    void should_response_secured_customer_by_secured_id() {
        given()
            .when()
            .get("/cust/customers/{securedId}", "secured_id_12")
            .then()
            .statusCode(200)
            .body("id", is("secured_id_12"))
            .body("name", is("小李"))
            .body("registeredAt", is("2025-08-08T00:00:00"));
    }

    @Test
    @DataSet("datasets/customer_empty.yaml")
    void should_response_secured_when_customer_registered() {
        given()
            .body(
                """
                    {
                        "name": "老李"
                    }
                    """
            )
            .when()
            .post("/cust/customers")
            .then()
            .log().all()
            .statusCode(200)
            .body("id", not(blankOrNullString()))
            .body("name", is("老李"))
            .body("registeredAt", not(blankOrNullString()));
    }
}
