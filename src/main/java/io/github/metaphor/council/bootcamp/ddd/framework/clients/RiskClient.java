package io.github.metaphor.council.bootcamp.ddd.framework.clients;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
@RequiredArgsConstructor
public class RiskClient {

    public CheckRiskResponse check(CheckRiskRequest request) {
        try {
            log.atWarn().log("RiskClient checker start to sleep {}", request);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.atError().withThrowable(e).log("RiskClient check failed {}", request);
            Thread.currentThread().interrupt();
        }

        log.atWarn().log("RiskClient checker start to work {}", request);
        if (request.getTotalAmount().compareTo(BigDecimal.valueOf(4000)) > 0) {
            return CheckRiskResponse.builder().risk(true).build();
        }
        return CheckRiskResponse.builder().risk(false).build();
    }


    @Getter
    @Builder
    @ToString
    public static class CheckRiskRequest {
        private BigDecimal totalAmount;
    }


    @Getter
    @Builder
    public static class CheckRiskResponse {
        private boolean risk;
    }
}
