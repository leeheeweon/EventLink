package com.project.eventlink.item.option.model;

import java.util.List;

public record UpdateOptionRequestModel(String name, List<UpdateOptionDetailRequestModel> updateOptionDetailRequestModels) {
}
