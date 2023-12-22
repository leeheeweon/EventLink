package com.project.eventlink.item.service;

import com.project.eventlink.item.model.FindItemResponseModel;

import java.util.List;

public interface ItemService {
    List<FindItemResponseModel> getItemList();
}
