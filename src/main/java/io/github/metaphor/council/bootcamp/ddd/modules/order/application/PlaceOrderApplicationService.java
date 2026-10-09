package io.github.metaphor.council.bootcamp.ddd.modules.order.application;

import io.github.metaphor.council.bootcamp.ddd.modules.order.application.mapper.OrderMapper;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.Order;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderDescription;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderFactory;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderLineDescription;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderPlacedEvent;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.OrderRepository;
import io.github.metaphor.council.bootcamp.ddd.modules.order.domain.model.RiskChecker;
import io.github.metaphor.council.bootcamp.ddd.framework.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PlaceOrderApplicationService {

    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final EventPublisher eventPublisher;
    private final RiskChecker riskChecker;

    // 可以去掉Command，使用领域值对象
    // 只操作一个聚合根（实体）事务边界
    // 业务编排，publish event
    // 优先最终一致性 （一个applicationservice要发多个event）
    // 编排（orchestration）
    // 抽象泄露
    // 模式：找出来（没有就新建），业务逻辑，编排，保存，发布事件
    // 隐形要求：建模能力 聚合根/实体/值对象

    @Transactional
    public Order place(OrderDescription description, List<OrderLineDescription> lineDescriptions) {
        Order order = orderFactory.place(description, lineDescriptions);

        riskChecker.check(order);

        orderRepository.save(order);

        eventPublisher.publish(toOrderPlacedEvent(order));
        return order;
    }

    private static OrderPlacedEvent toOrderPlacedEvent(Order order) {
        return OrderMapper.INSTANCE.toOrderPlacedEvent(order);
    }
}
