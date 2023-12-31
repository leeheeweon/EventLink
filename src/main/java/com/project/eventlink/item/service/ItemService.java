package com.project.eventlink.item.service;

import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.DeleteItemRequestModel;
import com.project.eventlink.item.model.FindItemResponseModel;
import com.project.eventlink.item.model.UpdateItemRequestModel;

import java.util.List;

public interface ItemService {
    List<FindItemResponseModel> getItemList(String keyword);

    FindItemResponseModel getItemDetail(Long itemId);

    Long addItem(CreateItemRequestModel createItemRequestModel);

    Long updateItem(UpdateItemRequestModel updateItemRequestModel);

    void deleteItem(DeleteItemRequestModel deleteItemRequestModel);
}
