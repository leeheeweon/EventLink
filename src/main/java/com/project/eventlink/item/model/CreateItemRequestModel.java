package com.project.eventlink.item.model;

import com.project.eventlink.item.option.model.CreateOptionRequestModel;

import java.util.List;

public record CreateItemRequestModel(
        String name,
        int price,
        int quantity,
        String detail,
        List<CreateOptionRequestModel> optionRequestModelList
) {}
