package com.project.eventlink.item.service;

import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.model.FindItemResponseModel;
import com.project.eventlink.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<FindItemResponseModel> getItemList() {

        List<Item> itemList = itemRepository.findAll();

        if (itemList.isEmpty()) {
            throw new RuntimeException("조회할 리스트가 없습니다"); //TODO 공통 에러로 전환해야함
        }

        return itemList.stream()
                .map(item -> new FindItemResponseModel(
                        item.getItemId(),
                        item.getName(),
                        item.getPrice(),
                        item.getStockQuantity(),
                        item.getDetail()
                )).collect(Collectors.toList());
    }
}
