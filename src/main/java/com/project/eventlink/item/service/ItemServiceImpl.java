package com.project.eventlink.item.service;

import com.project.eventlink.entity.SellStatus;
import com.project.eventlink.exception.CommonException;
import com.project.eventlink.exception.ExceptionType;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.model.*;
import com.project.eventlink.item.model.mapper.ItemMapper;
import com.project.eventlink.item.option.domain.Option;
import com.project.eventlink.item.option.domain.OptionDetail;
import com.project.eventlink.item.option.model.*;
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

        Item item = buildItem(createItemRequestModel);

        if (Objects.nonNull(createItemRequestModel.optionRequestModelList())) {
            createItemRequestModel.optionRequestModelList().forEach(createOptionRequestModel -> {
                Option option = buildCreateOption(item, createOptionRequestModel);

                createOptionRequestModel.createOptionDetailRequestModels()
                        .stream()
                        .map(detailRequestModel -> buildCreateOptionDetail(option, detailRequestModel))
                        .forEach(optionDetail -> {
                            option.addOptionDetail(optionDetail);
                            optionDetailRepository.save(optionDetail);
                        });

                optionRepository.save(option);

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

            optionRepository.deleteAllByItem(item);

            updateItemRequestModel.updateOptionRequestModels()
                    .forEach(updateOptionRequestModel -> {
                        Option option = buildUpdateOption(item, updateOptionRequestModel);
                        optionRepository.save(option);

                        updateOptionRequestModel.updateOptionDetailRequestModels().stream()
                                .map(updateOptionDetailRequestModel -> buildUpdateOptionDetail(option, updateOptionDetailRequestModel)).forEach(optionDetailRepository::save);
                    });
        }
        return item.getItemId();
    }

    @Transactional
    @Override
    public void deleteItem(DeleteItemRequestModel deleteItemRequestModel) {
        Item item = itemRepository.findById(deleteItemRequestModel.id())
                .orElseThrow(() -> new CommonException(ExceptionType.ITEM_NOT_FOUND));

        optionRepository.deleteAllByItem(item);
        itemRepository.deleteById(deleteItemRequestModel.id());
    }

    private Item buildItem(CreateItemRequestModel createItemRequestModel) {
        return Item.builder()
                .name(createItemRequestModel.name())
                .price(createItemRequestModel.price())
                .stockQuantity(createItemRequestModel.quantity())
                .detail(createItemRequestModel.detail())
                .sellStatus(SellStatus.OPEN)
                .build();
    }

    private Option buildCreateOption(Item item, CreateOptionRequestModel createOptionRequestModel) {
        return Option.builder()
                .item(item)
                .name(createOptionRequestModel.name())
                .build();
    }

    private OptionDetail buildCreateOptionDetail(Option option, CreateOptionDetailRequestModel detailRequestModel) {
        return OptionDetail.builder()
                .option(option)
                .name(detailRequestModel.name())
                .price(detailRequestModel.price())
                .stockQuantity(detailRequestModel.quantity())
                .build();
    }

    private Option buildUpdateOption(Item item, UpdateOptionRequestModel updateOptionRequestModel) {
        return Option.builder().item(item).name(updateOptionRequestModel.name()).build();
    }

    private OptionDetail buildUpdateOptionDetail(Option option, UpdateOptionDetailRequestModel updateOptionDetailRequestModel) {
        return OptionDetail.builder()
                .option(option)
                .name(updateOptionDetailRequestModel.name())
                .price(updateOptionDetailRequestModel.price())
                .stockQuantity(updateOptionDetailRequestModel.quantity())
                .build();
    }
}
