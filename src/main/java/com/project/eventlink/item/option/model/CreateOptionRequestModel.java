package com.project.eventlink.item.option.model;

import java.util.List;

public record CreateOptionRequestModel(String name, List<CreateOptionDetailRequestModel> createOptionDetailRequestModels) {
}
