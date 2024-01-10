-- Member
insert into member (member_id, password, age, name, street, city, zipcode, phone, photo, email, role, created_at)
values ('admin', '$2a$10$CYz1.XLZa66olzsE2SN3U.ahCQoZGvSfoD2RggZ5MsLO2o/PgkmSW', 30, '관리자', '서울특별시', '강남구',
        '12345', '010-1234-5678', null, 'admin@test.com', 'USER', '2023-04-01T10:15:30');
insert into member (member_id, password, age, name, street, city, zipcode, phone, photo, email, role, created_at)
values ('test', '$2a$10$3yWKZiL9sxSg0pMV7cRhpezmVCAsnKPwNEszey3M0s.oB5niHXEWi', 25, '일반', '경기도', '성남시 분당구',
        '11111', '010-1111-2222', null, 'a@test.com', 'USER', '2023-04-01T10:15:30');

-- Item
insert into item (name, price, stock_quantity, detail, sell_status, created_at, created_by, modified_at, modified_by)
values ('test-1', 1000, 100, 'test-detail', 'OPEN', '2023-04-01T10:15:30', 'admin', '2023-04-01T10:15:30', 'admin');
insert into item (name, price, stock_quantity, detail, sell_status, created_at, created_by, modified_at, modified_by)
values ('test-2', 2000, 200, 'test-detail2', 'OPEN', '2023-04-01T10:15:30', 'admin', '2023-04-01T10:15:30', 'admin');

-- Option
insert into option (name, item_id) values ('color', 1);
insert into option (name, item_id) values ('size', 1);

-- OptionDetail
insert into option_detail (name, price, stock_quantity, option_id) values ('black', 0, 10, 1);
insert into option_detail (name, price, stock_quantity, option_id) values ('white', 0, 10, 1);
insert into option_detail (name, price, stock_quantity, option_id) values ('small', 100, 10, 2);
insert into option_detail (name, price, stock_quantity, option_id) values ('large', 150, 10, 2);

-- Event
insert into event (event_id, name, min_price, created_at, created_by, modified_at, modified_by)
values (1, 'test_event', 1000, '2023-12-01 10:47:52.000', 'admin', '2023-12-01 10:47:52.000', 'admin');
