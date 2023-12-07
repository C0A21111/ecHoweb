
create table echodb.checkers(
    checker_id int primary key auto_increment,
    checker_name varchar(10) unique,
    checker_password varchar(10)
);

insert into checkers(checker_id,checker_name,checker_password) values
(null,'orner','');

create table echodb.items(
    item_id int primary key auto_increment,
    item_name varchar(50),
    item_price int,
    item_stock int default 0
);

insert into items(item_id,item_name,item_price) values
    (null,'パンフレット',3000),
    (null,'ポストカード_A',200),
    (null,'ポストカード_B',200),
    (null,'ペンライト',4000),
    (null,'ラバーストラップ_P',700),
    (null,'ラバーストラップ_B',700),
    (null,'ラバーストラップ_G',700),
    (null,'ラバーストラップ_O',700),
    (null,'ラバーバンド_P',600),
    (null,'ラバーバンド_B',600),
    (null,'ラバーバンド_G',600),
    (null,'ラバーバンド_O',600),
    (null,'ランダム缶バッジ',550),
    (null,'アクリルキーホルダー_A',750),
    (null,'アクリルキーホルダー_B',750),
    (null,'タオル',1900),
    (null,'エコバッグ',1800),
    (null,'Tシャツ_A',3000),
    (null,'Tシャツ_B',3000),
    (null,'パーカー_A',5500),
    (null,'パーカー_B',5500),
    (null,'シュシュ_P',700),
    (null,'シュシュ_B',700),
    (null,'シュシュ_G',700),
    (null,'シュシュ_O',700);

update items set item_stock = 10;

create table echodb.users(
    docked_number int primary key auto_increment,
    user_name varchar(12),
    status int default 0
);

insert into users(user_name) values
    ("ひろみ1"), ("ひろみ2"), ("ひろみ3"), ("ひろみ4"), ("ひろみ5"),
    ("ひろみ6"), ("ひろみ7"), ("ひろみ8"), ("ひろみ9"), ("ひろみ10"),
    ("ひろみ11"),("ひろみ12"),("ひろみ13"),("ひろみ14"),("ひろみ15"),
    ("ひろみ16"),("ひろみ17"),("ひろみ18"),("ひろみ19"),("ひろみ20"),
    ("ひろみ21"),("ひろみ22"),("ひろみ23"),("ひろみ24"),("ひろみ25"),
    ("ひろみ26"),("ひろみ27"),("ひろみ28"),("ひろみ29"),("ひろみ30"),
    ("ひろみ31"),("ひろみ32"),("ひろみ33"),("ひろみ34"),("ひろみ35"),
    ("ひろみ36"),("ひろみ37"),("ひろみ38"),("ひろみ39"),("ひろみ40");

update users set status=3 where docked_number <= 13;
update users set status=2 where docked_number > 13 and docked_number <= 38;
update users set status=1 where docked_number > 38 and docked_number <= 40;
-- update users set status=0 where docked_number > 29;

update users set status=3 where docked_number <= 38;
update users set status=3 where docked_number = 39;
update users set status=1 where docked_number = 40;
-- update users set status=3 where docked_number <= 40;

create table echodb.orders(
    order_id int primary key auto_increment,
    docked_number int  references users(docked_number),
    item_id int references items(item_id),
    item_count int
);

insert into orders(docked_number,item_id,item_count) values
    (1, 3, 10),
    (1, 4, 5),
    (2, 3, 5),
    (2, 6, 8),
    (2, 5, 6),
    (3, 2, 50),
    (4, 3, 3),
    (4, 4, 2),
    (5, 5, 4),
    (6, 6, 1),
    (7, 7, 10),
    (8, 8, 12),
    (9, 9,2),
    (10, 10, 3),
    (11, 11, 2),
    (12, 12, 9),
    (13, 13, 2),
    (14, 14, 19),
    (15, 15, 15),
    (16, 16, 4),
    (17, 17, 2),
    (18, 18, 3),
    (19, 19, 7),
    (20, 6, 5),
    (21, 21, 1),
    (22, 22, 3),
    (23, 23, 8),
    (24, 24, 8),
    (25, 25, 3),
    (26, 1, 2),
    (27, 2, 7),
    (28, 3, 3),
    (29, 4, 4),
    (30, 5, 5),
    (31, 6, 9),
    (32, 7, 5),
    (33, 8, 9),
    (34, 9, 5),
    (35, 10, 2),
    (36, 11, 2),
    (37, 12, 11),
    (38, 13, 1),
    (39, 14, 4),
    (40, 5, 6);


