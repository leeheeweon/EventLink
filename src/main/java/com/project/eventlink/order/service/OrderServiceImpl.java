package com.project.eventlink.order.service;

import com.project.eventlink.entity.Address;
import com.project.eventlink.entity.Delivery;
import com.project.eventlink.exception.CommonException;
import com.project.eventlink.exception.ExceptionType;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.repository.ItemRepository;
import com.project.eventlink.order.domain.Order;
import com.project.eventlink.order.domain.OrderStatus;
import com.project.eventlink.order.model.CreateOrderModel;
import com.project.eventlink.order.repository.DeliveryRepository;
import com.project.eventlink.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    @Transactional
    public Long makeOrder(CreateOrderModel createOrderModel) {
        Item findItem = itemRepository.findById(createOrderModel.itemId())
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));

        Delivery delivery = Delivery.builder()
                .address(new Address(
                        createOrderModel.city(),
                        createOrderModel.street(),
                        createOrderModel.zipcode()
                )).build();

        deliveryRepository.save(delivery);

        Order order = Order.builder()
                .member(findItem.getMember())
                .orderStatus(OrderStatus.ORDER)
                .delivery(delivery)
                .build();

        return orderRepository.save(order).getOrderId();
    }
}
