package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.item.model.*;
import com.project.eventlink.item.option.model.CreateOptionDetailRequestModel;
import com.project.eventlink.item.option.model.CreateOptionRequestModel;
import com.project.eventlink.item.option.model.UpdateOptionDetailRequestModel;
import com.project.eventlink.item.option.model.UpdateOptionRequestModel;
import com.project.eventlink.item.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ItemServiceTest extends BaseSpringBootTest {

    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("아이템을 추가한다 - 옵션 없음")
    @Transactional
    void addItem() {
        //given
        CreateItemRequestModel createItemRequestModel = new CreateItemRequestModel("test", 1000, 10, "테스트아이템", null);

        //when
        Long itemId = itemService.addItem(createItemRequestModel);
        FindItemModel findItem = itemService.getItemDetail(itemId);

        //then
        assertThat(findItem.name()).isEqualTo("test");
    }

    @Test
    @DisplayName("아이템을 추가한다 - 옵션 있음")
    @Transactional
    void addItemWithOption() {
        //given
        CreateOptionRequestModel createOptionRequestModel = new CreateOptionRequestModel("test-option",
                List.of(new CreateOptionDetailRequestModel("test-detail-1", 1000, 10),
                        new CreateOptionDetailRequestModel("test-detail-2", 500, 15)));
        CreateItemRequestModel createItemRequestModel = new CreateItemRequestModel("test-with-option", 1000, 10, "테스트아이템-옵션", List.of(createOptionRequestModel));

        //when
        Long itemId = itemService.addItem(createItemRequestModel);
        FindItemModel findItem = itemService.getItemDetail(itemId);

        //then
        assertThat(findItem.name()).isEqualTo("test-with-option");
    }

    @Test
    @DisplayName("아이템을 수정한다 - 옵션 없음")
    @Transactional
    void updateItem() {
        //given
        UpdateItemRequestModel updateItemRequestModel = new UpdateItemRequestModel(2L, "update", 20000, 10, "update_detail", "CLOSE", null);
        FindItemModel beforeItemDetail = itemService.getItemDetail(updateItemRequestModel.id());

        //when
        Long itemId = itemService.updateItem(updateItemRequestModel);
        FindItemModel afterItemDetail = itemService.getItemDetail(itemId);

        //then
        assertAll(
                () -> assertThat(beforeItemDetail.name()).isEqualTo("test-2"),
                () -> assertThat(afterItemDetail.name()).isEqualTo("update")
        );
    }

    @Test
    @DisplayName("아이템을 수정한다 - 옵션 있음")
    @Transactional
    void updateItemWithOption() {
        //given
        UpdateOptionRequestModel updateOptionRequestModel = new UpdateOptionRequestModel("test-option",
                List.of(new UpdateOptionDetailRequestModel("test-detail-3", 2000, 20),
                        new UpdateOptionDetailRequestModel("test-detail-4", 600, 25)));
        UpdateItemRequestModel updateItemRequestModel = new UpdateItemRequestModel(1L, "update", 20000, 10, "update_detail", "CLOSE", List.of(updateOptionRequestModel));
        FindItemModel beforeItemDetail = itemService.getItemDetail(updateItemRequestModel.id());

        //when
        Long itemId = itemService.updateItem(updateItemRequestModel);
        FindItemModel afterItemDetail = itemService.getItemDetail(itemId);

        //then
        assertAll(
                () -> assertThat(beforeItemDetail.name()).isEqualTo("test-1"),
                () -> assertThat(afterItemDetail.name()).isEqualTo("update"),
                () -> assertThat(afterItemDetail.optionModelList().get(0).name()).isEqualTo("test-option"),
                () -> assertThat(afterItemDetail.optionModelList().get(0).optionDetailModelList()).extracting("name").contains("test-detail-3", "test-detail-4")
        );
    }

    @Test
    @DisplayName("아이템 리스트를 가져온다")
    void itemList() {
        //given

        //when
        List<FindItemListModel> itemList = itemService.getItemList("test");

        //then
        assertAll(
                () -> assertThat(itemList).extracting("name").asList()
                        .isNotEmpty().allMatch(name -> ((String) name).contains("test")),
                () -> assertThat(itemList).isNotEmpty()
        );
    }

    @Test
    @DisplayName("아이템 상세 조회한다 - 옵션 있음")
    void itemDetail() {
        //given
        Long itemId = 1L;

        //when
        FindItemModel findItem = itemService.getItemDetail(itemId);

        //then
        assertAll(
                () -> assertThat(findItem.itemId()).isEqualTo(1),
                () -> assertThat(findItem.optionModelList())
                        .extracting("optionId")
                        .containsExactly(1L, 2L),
                () -> assertThat(findItem.optionModelList().get(0).optionDetailModelList())
                        .extracting("name")
                        .contains("black", "white"),
                () -> assertThat(findItem.optionModelList().get(1).optionDetailModelList())
                        .extracting("name")
                        .contains("small", "large")
        );
    }

    @Test
    @DisplayName("아이템 상세 조회한다 - 옵션 없음")
    void itemDetailNoOption() {
        //given
        Long itemId = 2L;

        //when
        FindItemModel findItem = itemService.getItemDetail(itemId);

        //then
        assertThat(findItem.itemId()).isEqualTo(2L);
        assertThat(findItem.optionModelList()).isNull();
    }

    @Test
    @DisplayName("아이템을 삭제한다")
    @Transactional
    void deleteItem() {
        //given
        List<FindItemListModel> beforeItemList = itemService.getItemList("");

        //when
        itemService.deleteItem(new DeleteItemRequestModel(2L));
        List<FindItemListModel> afterItemList = itemService.getItemList("");

        //then
        assertThat(afterItemList).hasSizeLessThan(beforeItemList.size());
    }
}
