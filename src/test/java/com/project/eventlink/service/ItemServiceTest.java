package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.DeleteItemRequestModel;
import com.project.eventlink.item.model.FindItemResponseModel;
import com.project.eventlink.item.model.UpdateItemRequestModel;
import com.project.eventlink.item.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ItemServiceTest extends BaseSpringBootTest {

    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("아이템을 추가한다")
    void addItem() {
        //given
        CreateItemRequestModel createItemRequestModel = new CreateItemRequestModel("test", 1000, 10, "테스트아이템");

        //when
        Long itemId = itemService.addItem(createItemRequestModel);

        //then
        assertThat(itemId).isEqualTo(3);
    }

    @Test
    @DisplayName("아이템을 수정한다")
    void updateItem() {
        //given
        UpdateItemRequestModel updateItemRequestModel = new UpdateItemRequestModel(1L, "update", 20000, 10, "update_detail", "CLOSE");
        FindItemResponseModel beforeItemDetail = itemService.getItemDetail(updateItemRequestModel.id());

        //when
        Long itemId = itemService.updateItem(updateItemRequestModel);
        FindItemResponseModel afterItemDetail = itemService.getItemDetail(itemId);

        //then
        assertAll(
                () -> assertThat(beforeItemDetail.name()).isEqualTo("test-1"),
                () -> assertThat(afterItemDetail.name()).isEqualTo("update")
        );
    }

    @Test
    @DisplayName("아이템 리스트를 가져온다")
    void itemList() {
        //given

        //when
        List<FindItemResponseModel> itemList = itemService.getItemList("test");

        //then
        assertAll(
                () -> assertThat(itemList).extracting("name").asList()
                        .isNotEmpty().allMatch(name -> ((String) name).contains("test")),
                () -> assertThat(itemList).hasSize(2)
        );
    }

    @Test
    @DisplayName("아이템을 삭제한다")
    void deleteItem() {
        //given
        itemService.deleteItem(new DeleteItemRequestModel(3L));

        //when
        List<FindItemResponseModel> itemList = itemService.getItemList("test");

        //then
        assertThat(itemList).hasSize(1);
    }
}
