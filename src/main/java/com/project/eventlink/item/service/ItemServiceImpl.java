package com.project.eventlink.item.service;

import com.project.eventlink.entity.SellStatus;
import com.project.eventlink.exception.CommonException;
import com.project.eventlink.exception.ExceptionType;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.DeleteItemRequestModel;
import com.project.eventlink.item.model.FindItemResponseModel;
import com.project.eventlink.item.model.UpdateItemRequestModel;
import com.project.eventlink.item.model.mapper.ItemMapper;
import com.project.eventlink.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FindItemResponseModel> getItemList(String keyword) {

        List<FindItemResponseModel> itemList = itemRepository.searchItemList(keyword);
        if (itemList.isEmpty()) {
            throw new CommonException(ExceptionType.ITEM_LIST_EMPTY);
        }
        return itemList;
    }

    @Transactional(readOnly = true)
    @Override
    public FindItemResponseModel getItemDetail(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));
        return itemMapper.toItemResponseModel(item);
    }

    @Transactional
    @Override
    public Long addItem(CreateItemRequestModel createItemRequestModel) {

        Item item = Item.builder()
                .name(createItemRequestModel.name())
                .price(createItemRequestModel.price())
                .stockQuantity(createItemRequestModel.quantity())
                .detail(createItemRequestModel.detail())
                .sellStatus(SellStatus.OPEN)
                .build();

        return itemRepository.save(item).getItemId();
    }

    @Transactional
    @Override
    public Long updateItem(UpdateItemRequestModel updateItemRequestModel) {
        Item item = itemRepository.findById(updateItemRequestModel.id())
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));

        item.updateItem(updateItemRequestModel);

        return item.getItemId();
    }

    @Transactional
    @Override
    public void deleteItem(DeleteItemRequestModel deleteItemRequestModel) {
        itemRepository.findById(deleteItemRequestModel.id())
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));

        // TODO 되도록 soft delete 하는 것이 좋음 - 현재는 delete flag 값이 없어 hard delete 처리
        itemRepository.deleteById(deleteItemRequestModel.id());
    }
}
