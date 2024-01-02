package com.project.eventlink.item.repository;

import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.model.FindItemResponseModel;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QItemRepository {

    List<FindItemResponseModel> searchItemList(String keyword);

}
