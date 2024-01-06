package com.project.eventlink.cart.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCartRequestModel(
        String memberId,
        @NotNull(message = "상품 아이디는 필수 입력 값 입니다.") Long itemId,
        @Min(value = 1, message = "최소 1개 이상 담아주세요") int count
) {
}
