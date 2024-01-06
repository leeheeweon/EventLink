package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.model.DeleteItemRequestModel;
import com.project.eventlink.item.model.FindItemResponseModel;
import com.project.eventlink.item.model.UpdateItemRequestModel;
import com.project.eventlink.item.option.model.CreateOptionDetailRequestModel;
import com.project.eventlink.item.option.model.CreateOptionRequestModel;
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
    @DisplayName("아이템을 추가한다 - 옵션 없음")
    void addItem() {
        //given
        CreateItemRequestModel createItemRequestModel = new CreateItemRequestModel("test", 1000, 10, "테스트아이템", null);

        //when
        Long itemId = itemService.addItem(createItemRequestModel);
        FindItemResponseModel findItem = itemService.getItemDetail(itemId);

        //then
        assertThat(findItem.name()).isEqualTo("test");
    }

    @Test
    @DisplayName("아이템을 추가한다 - 옵션 있음")
    void addItemWithOption() {
        //given
        CreateOptionRequestModel createOptionRequestModel = new CreateOptionRequestModel("test-option",
                List.of(new CreateOptionDetailRequestModel("test-detail-1", 1000, 10),
                        new CreateOptionDetailRequestModel("test-detail-2", 500, 15)));
        CreateItemRequestModel createItemRequestModel = new CreateItemRequestModel("test-with-option", 1000, 10, "테스트아이템-옵션", List.of(createOptionRequestModel));

        //when
        Long itemId = itemService.addItem(createItemRequestModel);
        FindItemResponseModel findItem = itemService.getItemDetail(itemId);

        //then
        assertThat(findItem.name()).isEqualTo("test-with-option");
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
                () -> assertThat(itemList).isNotEmpty()
        );
    }

    @Test
    @DisplayName("아이템을 삭제한다")
    void deleteItem() {
        //given
        List<FindItemResponseModel> beforeItemList = itemService.getItemList("");

        //when
        itemService.deleteItem(new DeleteItemRequestModel(2L));
        List<FindItemResponseModel> afterItemList = itemService.getItemList("");

        //then
        assertThat(afterItemList).hasSizeLessThan(beforeItemList.size());
    }
}
