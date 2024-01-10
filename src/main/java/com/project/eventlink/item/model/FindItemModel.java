package com.project.eventlink.item.model;

import com.project.eventlink.item.option.model.FindOptionModel;

import java.util.List;

public record FindItemModel(Long itemId, String name, int price, int stockQuantity, String detail,
                            List<FindOptionModel> optionModelList){

}
