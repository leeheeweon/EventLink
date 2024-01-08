package com.project.eventlink.item.option.model;

import java.util.List;

public record FindOptionModel(Long optionId, String name,
                              List<FindOptionDetailModel> optionDetailModelList) {
}
