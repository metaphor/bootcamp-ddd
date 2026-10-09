package com.newchiwan.council.bootcamp.ddd.ft.customer;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.newchiwan.council.bootcamp.ddd.modules.catalog.domain.model.ProductRepository;
import com.newchiwan.council.bootcamp.ddd.modules.catalog.domain.model.ProductStatus;
import com.newchiwan.council.bootcamp.ddd.support.FunctionalTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class OrderFunctionalTest extends FunctionalTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    @DataSet({
        "datasets/product_single.yaml",
    })
    void should_test_product_description() {
        transactionTemplate.executeWithoutResult(status -> {
            var product = productRepository.ofIds(List.of("p001")).get(0);
            assertThat(product.getDescription().getStatus()).isEqualTo(ProductStatus.PUBLISHED);

            product.draft();

            productRepository.save(product);
        });
    }

    @Test
    @DataSet({
        "datasets/customer_single.yaml",
        "datasets/product_single.yaml",
        "datasets/inventory_single.yaml",
        "datasets/order_empty.yaml"
    })
    @ExpectedDataSet("datasets/inventory_single_stock_3.yaml")
    void should_response_order_when_place_order() {
        given()
            .body(
                """
                    {
                        "customerId": "cust001",
                        "remark": "My birthday gift.",
                        "lines": [
                            {
                                "productId": "p001",
                                "quantity": 2
                            }
                        ]
                    }
                    """
            )
            .when()
            .post("/cust/orders")
            .then()
            .log().body()
            .statusCode(200)
            .body("id", not(blankOrNullString()))
            .body("remark", is("My birthday gift."))
            .body("totalAmount", is(197.96F))
            .body("status", is("PLACED"))
            .body("placedAt", not(blankOrNullString()))
            .body("lines", hasSize(1))
            .body("lines[0].product.id", is("p001"))
            .body("lines[0].product.name", is("Macbook Air"))
            .body("lines[0].product.price", is(98.98F))
            .body("lines[0].quantity", is(2))
            .body("lines[0].totalAmount", is(197.96F));
    }

    @Test
    @DataSet({
        "datasets/customer_single.yaml",
        "datasets/product_single.yaml",
        "datasets/inventory_single.yaml",
        "datasets/order_empty.yaml"
    })
    @ExpectedDataSet({
        "datasets/inventory_single.yaml",
        "datasets/order_empty.yaml"
    })
    void should_response_out_of_stock_when_place_high_quantity_order() {
        given()
            .body(
                """
                    {
                        "customerId": "cust001",
                        "remark": "My birthday gift.",
                        "lines": [
                            {
                                "productId": "p001",
                                "quantity": 6
                            }
                        ]
                    }
                    """
            )
            .when()
            .post("/cust/orders")
            .then()
            .log().body()
            .statusCode(400)
            .body("code", is("OUT_OF_STOCK"));
    }

    @Test
    @DataSet({
        "datasets/customer_single.yaml",
        "datasets/product_single.yaml",
        "datasets/inventory_single.yaml",
        "datasets/order_empty.yaml"
    })
    @ExpectedDataSet({
        "datasets/inventory_single_stock_3.yaml"
    })
    void should_response_order_when_place_order_oo_version() {
        given()
            .body(
                """
                    {
                        "customerId": "cust001",
                        "remark": "My birthday gift.",
                        "lines": [
                            {
                                "productId": "p001",
                                "quantity": 2
                            }
                        ]
                    }
                    """
            )
            .when()
            .post("/cust/orders/oo-version")
            .then()
            .log().body()
            .statusCode(200)
            .body("id", not(blankOrNullString()))
            .body("remark", is("My birthday gift."))
            .body("totalAmount", is(197.96F))
            .body("status", is("PLACED"))
            .body("placedAt", not(blankOrNullString()))
            .body("lines", hasSize(1))
            .body("lines[0].product.id", is("p001"))
            .body("lines[0].product.name", is("Macbook Air"))
            .body("lines[0].product.price", is(98.98F))
            .body("lines[0].quantity", is(2))
            .body("lines[0].totalAmount", is(197.96F));
    }

    @Test
    @DataSet({
        "datasets/customer_single.yaml",
        "datasets/product_single.yaml",
        "datasets/inventory_single.yaml",
        "datasets/order_empty.yaml"
    })
    @ExpectedDataSet({
        "datasets/inventory_single.yaml",
        "datasets/order_empty.yaml"
    })
    void should_response_out_of_stock_when_place_high_quantity_order_oo_version() {
        given()
            .body(
                """
                    {
                        "customerId": "cust001",
                        "remark": "My birthday gift.",
                        "lines": [
                            {
                                "productId": "p001",
                                "quantity": 6
                            }
                        ]
                    }
                    """
            )
            .when()
            .post("/cust/orders/oo-version")
            .then()
            .log().body()
            .statusCode(400)
            .body("code", is("OUT_OF_STOCK"));
    }


    @Test
    @DataSet({
        "datasets/order_single.yaml"
    })
    @ExpectedDataSet("datasets/order_single_paid.yaml")
    void should_checkout_order() {
        given()
            .when()
            .post("/cust/orders/{id}/checkout", "order001")
            .then()
            .log().body()
            .statusCode(200);
    }
}
