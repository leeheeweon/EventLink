package com.project.eventlink.item.repository;

import com.project.eventlink.item.domain.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QItemRepositoryImpl implements QItemRepository {

    private final JPAQueryFactory queryFactory;

    private final QItem item = QItem.item;


}
