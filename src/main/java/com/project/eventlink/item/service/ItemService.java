package com.project.eventlink.item.service;

import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.DeleteItemRequestModel;
import com.project.eventlink.item.model.FindItemModel;
import com.project.eventlink.item.model.UpdateItemRequestModel;

import java.util.List;

public interface ItemService {
    List<FindItemModel> getItemList(String keyword);

    FindItemModel getItemDetail(Long itemId);

    Long addItem(CreateItemRequestModel createItemRequestModel);

    Long updateItem(UpdateItemRequestModel updateItemRequestModel);

    void deleteItem(DeleteItemRequestModel deleteItemRequestModel);
}
