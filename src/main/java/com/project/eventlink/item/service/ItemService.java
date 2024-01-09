package com.project.eventlink.item.service;

import com.project.eventlink.item.model.*;

import java.util.List;

public interface ItemService {
    List<FindItemListModel> getItemList(String keyword);

    FindItemModel getItemDetail(Long itemId);

    Long addItem(CreateItemRequestModel createItemRequestModel);

    Long updateItem(UpdateItemRequestModel updateItemRequestModel);

    void deleteItem(DeleteItemRequestModel deleteItemRequestModel);
}
