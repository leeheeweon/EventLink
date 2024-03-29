package com.project.eventlink.item.repository;

import com.project.eventlink.item.domain.QItem;
import com.project.eventlink.item.model.FindItemListModel;
import com.project.eventlink.item.model.FindItemModel;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.internal.compiler.ast.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QItemRepositoryImpl implements QItemRepository {

    private final JPAQueryFactory queryFactory;

    private final QItem item = QItem.item;

    @Override
    public List<FindItemListModel> searchItemList(String keyword) {
        return queryFactory.select(Projections.constructor(FindItemListModel.class,
                        item.itemId,
                        item.name,
                        item.price,
                        item.stockQuantity,
                        item.detail))
                .from(item)
                .where(item.name.contains(keyword))
                .fetch();
    }
}
