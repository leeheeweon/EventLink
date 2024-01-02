insert into item (name, price, stock_quantity, detail, sell_status, created_at, created_by, modified_at, modified_by)
values ('test-1', 1000, 100, 'test-detail', 'OPEN', '2023-04-01T10:15:30', 'admin', '2023-04-01T10:15:30', 'admin');
insert into item (name, price, stock_quantity, detail, sell_status, created_at, created_by, modified_at, modified_by)
values ('test-2', 2000, 200, 'test-detail2', 'OPEN', '2023-04-01T10:15:30', 'admin', '2023-04-01T10:15:30', 'admin');

insert into event (event_id, name, min_price, created_at, created_by, modified_at, modified_by)
values (1, 'test_event', 1000, '2023-12-01 10:47:52.000', 'admin', '2023-12-01 10:47:52.000', 'admin');
