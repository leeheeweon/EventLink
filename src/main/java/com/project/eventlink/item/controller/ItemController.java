package com.project.eventlink.item.controller;

import com.project.eventlink.common.model.CommonResponse;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.DeleteItemRequestModel;
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
    public CommonResponse itemList(@RequestParam String keyword) {
        return CommonResponse.toResponse(HttpStatus.OK, itemService.getItemList(keyword));
    }

    @GetMapping("/detail/{itemId}")
    public CommonResponse itemDetail(@PathVariable Long itemId) {
        return CommonResponse.toResponse(HttpStatus.OK, itemService.getItemDetail(itemId));
    }

    @PostMapping("/add")
    public CommonResponse addItem(@RequestHeader("authorization") String authorization, @RequestBody CreateItemRequestModel createItemRequestModel) {
        return CommonResponse.toResponse(HttpStatus.CREATED, itemService.addItem(createItemRequestModel));
    }

    @PutMapping("/update")
    public CommonResponse updateItem(@RequestHeader("authorization") String authorization, @RequestBody UpdateItemRequestModel updateItemRequestModel) {
        return CommonResponse.toResponse(HttpStatus.OK, itemService.updateItem(updateItemRequestModel));
    }

    @DeleteMapping("/delete")
    public CommonResponse deleteItem(@RequestHeader("authorization") String authorization, @RequestBody DeleteItemRequestModel deleteItemRequestModel) {
        itemService.deleteItem(deleteItemRequestModel);
        return CommonResponse.toResponse(HttpStatus.OK, deleteItemRequestModel.id());
    }
}
