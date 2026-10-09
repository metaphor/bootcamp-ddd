package io.github.metaphor.council.bootcamp.ddd.interfaces.customer.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlaceOrderRequest {
    private String customerId;
    private String remark;
    private List<OrderLine> lines = new ArrayList<>();

    @Data
    public static class OrderLine {
        private String productId;
        private int quantity;
    }
}
