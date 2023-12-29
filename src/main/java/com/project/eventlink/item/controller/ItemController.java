package com.project.eventlink.item.controller;

import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.UpdateItemRequestModel;
import com.project.eventlink.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public CommonResponse itemList() {
        return CommonResponse.toResponse(HttpStatus.OK, itemService.getItemList());
    }

    @PostMapping("/add")
    public CommonResponse addItem(@RequestHeader("authorization") String authorization, CreateItemRequestModel createItemRequestModel) {
        return CommonResponse.toResponse(HttpStatus.CREATED, null);
    }

    @PutMapping("/update")
    public CommonResponse updateItem(@RequestHeader("authorization") String authorization, UpdateItemRequestModel updateItemRequestModel) {
        return CommonResponse.toResponse(HttpStatus.OK, null);
    }

    @DeleteMapping("/delete")
    public CommonResponse updateItem(@RequestHeader("authorization") String authorization, Long id) {
        return CommonResponse.toResponse(HttpStatus.OK, null);
    }
}
