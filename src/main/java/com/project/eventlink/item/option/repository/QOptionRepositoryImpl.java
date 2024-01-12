package com.project.eventlink.item.option.repository;

import com.project.eventlink.item.option.domain.QOption;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QOptionRepositoryImpl implements QOptionRepository {

    private final JPAQueryFactory queryFactory;
    private QOption option = QOption.option;

    @Override
    public long deleteOptionsByItemId(Long itemId) {
        return queryFactory.delete(option).where(option.item.itemId.eq(itemId)).execute();
    }
}
