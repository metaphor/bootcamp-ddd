package com.newchiwan.council.bootcamp.ddd.modules.order.infrastructure;

import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.Order;
import com.newchiwan.council.bootcamp.ddd.modules.order.domain.model.RiskChecker;
import com.newchiwan.council.bootcamp.ddd.framework.domain.exception.InRiskException;
import com.newchiwan.council.bootcamp.ddd.framework.clients.RiskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoteRiskChecker implements RiskChecker {

    private final RiskClient riskClient;

    @Override
    public void check(Order order) {
        RiskClient.CheckRiskRequest request = RiskClient.CheckRiskRequest.builder()
            .totalAmount(order.getTotalAmount())
            .build();
        RiskClient.CheckRiskResponse response = riskClient.check(request);
        if (response.isRisk()) {
            throw new InRiskException();
        }
    }
}
