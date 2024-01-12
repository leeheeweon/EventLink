package com.project.eventlink.item.option.model;

import java.util.List;

public record UpdateOptionRequestModel(Long id, String name, List<UpdateOptionDetailRequestModel> updateOptionDetailRequestModels) {
}
