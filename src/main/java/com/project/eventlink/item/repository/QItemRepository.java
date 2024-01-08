package com.project.eventlink.item.repository;

import com.project.eventlink.item.model.FindItemModel;

import java.util.List;

public interface QItemRepository {

    List<FindItemModel> searchItemList(String keyword);

}
