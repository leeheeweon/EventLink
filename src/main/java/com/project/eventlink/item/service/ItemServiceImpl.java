package com.project.eventlink.item.service;

import com.project.eventlink.entity.SellStatus;
import com.project.eventlink.exception.CommonException;
import com.project.eventlink.exception.ExceptionType;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.model.*;
import com.project.eventlink.item.model.mapper.ItemMapper;
import com.project.eventlink.item.option.domain.Option;
import com.project.eventlink.item.option.domain.OptionDetail;
import com.project.eventlink.item.option.model.FindOptionDetailModel;
import com.project.eventlink.item.option.model.FindOptionModel;
import com.project.eventlink.item.option.repository.OptionDetailRepository;
import com.project.eventlink.item.option.repository.OptionRepository;
import com.project.eventlink.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;
    private final OptionDetailRepository optionDetailRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FindItemListModel> getItemList(String keyword) {

        List<FindItemListModel> itemList = itemRepository.searchItemList(keyword);
        if (itemList.isEmpty()) {
            throw new CommonException(ExceptionType.ITEM_LIST_EMPTY);
        }
        return itemList;
    }

    @Transactional(readOnly = true)
    @Override
    public FindItemModel getItemDetail(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));

        List<Option> optionList = optionRepository.findByItemItemId(item.getItemId());

        List<FindOptionModel> findOptionModels = optionList.isEmpty() ? null : optionList.stream()
                .map(option -> new FindOptionModel(option.getOptionId(), option.getName(),
                        optionDetailRepository.findOptionDetailByOptionOptionId(option.getOptionId())
                                .stream()
                                .map(itemMapper::toOptionDetailModel)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        return new FindItemModel(item.getItemId(), item.getName(), item.getPrice(),
                item.getStockQuantity(), item.getDetail(), findOptionModels);
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

        if (Objects.nonNull(createItemRequestModel.optionRequestModelList())) {
            createItemRequestModel.optionRequestModelList().forEach(createOptionRequestModel -> {
                Option option = Option.builder().item(item).name(createOptionRequestModel.name()).build();
                optionRepository.save(option);

                createOptionRequestModel.createOptionDetailRequestModels().stream()
                        .map(detailRequestModel ->
                                OptionDetail.builder()
                                        .option(option)
                                        .name(detailRequestModel.name())
                                        .price(detailRequestModel.price())
                                        .stockQuantity(detailRequestModel.quantity())
                                        .build()).forEach(optionDetailRepository::save);
            });
        }

        return itemRepository.save(item).getItemId();
    }

    @Transactional
    @Override
    public Long updateItem(UpdateItemRequestModel updateItemRequestModel) {
        Item item = itemRepository.findById(updateItemRequestModel.id())
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));

        item.updateItem(updateItemRequestModel);

        if (Objects.nonNull(updateItemRequestModel.updateOptionRequestModels())) {
            optionRepository.deleteAllByItemItemId(item.getItemId());
            updateItemRequestModel.updateOptionRequestModels().forEach(updateOptionRequestModel -> {
                Option option = Option.builder().item(item).name(updateOptionRequestModel.name()).build();
                optionRepository.save(option);

                updateOptionRequestModel.updateOptionDetailRequestModels().stream()
                        .map(updateOptionDetailRequestModel ->
                                OptionDetail.builder()
                                        .option(option)
                                        .name(updateOptionDetailRequestModel.name())
                                        .price(updateOptionDetailRequestModel.price())
                                        .stockQuantity(updateOptionDetailRequestModel.quantity())
                                        .build()).forEach(optionDetailRepository::save);
            });
        }
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
