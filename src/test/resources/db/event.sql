-- 모든 제약 조건 비활성화
SET REFERENTIAL_INTEGRITY FALSE;
truncate table event;
insert into event (name, min_price, member_id, created_at, created_by, modified_at, modified_by) values('test_event', 1000, 1, '2023-12-01 10:47:52.000', 1, '2023-12-01 10:47:52.000', 1);

SET REFERENTIAL_INTEGRITY TRUE;
-- 모든 제약 조건 활성화