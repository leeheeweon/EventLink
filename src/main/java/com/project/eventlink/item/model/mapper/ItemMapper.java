package com.project.eventlink.item.model.mapper;

import com.project.eventlink.item.domain.Item;
import com.project.eventlink.item.model.FindItemResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    FindItemResponseModel toItemResponseModel(Item item);
}
