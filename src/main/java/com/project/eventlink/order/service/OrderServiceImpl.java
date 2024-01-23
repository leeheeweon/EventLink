package com.project.eventlink.order.service;

import com.project.eventlink.exception.CommonException;
import com.project.eventlink.exception.ExceptionType;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.repository.ItemRepository;
import com.project.eventlink.order.model.CreateOrderModel;
import com.project.eventlink.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Override
    public Long makeOrder(CreateOrderModel createOrderModel) {
        Item findItem = itemRepository.findById(createOrderModel.itemId())
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));
        return null;
    }
}
