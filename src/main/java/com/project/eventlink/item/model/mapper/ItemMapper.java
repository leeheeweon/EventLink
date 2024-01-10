package com.project.eventlink.item.model.mapper;

import com.project.eventlink.item.option.domain.OptionDetail;
import com.project.eventlink.item.option.model.FindOptionDetailModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    FindOptionDetailModel toOptionDetailModel(OptionDetail optionDetail);
}
