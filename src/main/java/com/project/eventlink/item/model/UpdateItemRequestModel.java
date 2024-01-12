package com.project.eventlink.item.model;

import com.project.eventlink.item.option.model.UpdateOptionRequestModel;

import java.util.List;

public record UpdateItemRequestModel(Long id,
                                     String name,
                                     int price,
                                     int quantity,
                                     String detail,
                                     String sellStatus,
                                     List<UpdateOptionRequestModel> updateOptionRequestModels) {
}
