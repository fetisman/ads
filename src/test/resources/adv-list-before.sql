delete from adv;
delete from adv_type;
delete from catalog;

insert into catalog(id, title) values
(0, 'all'),
(1, 'root 1'),
(2, 'root 2');

insert into adv_type(id, adv_type, catalog_id) values
(1, 'root 1.1', 1),
(2, 'root 2.2', 2);


insert into adv(id, long_desc, phone, price, short_desc, user_id, type, adv_type_id) values
(1, 'aaa', '111 11 11', '100', 'a', 1, 'root 1.1', 1),
(2, 'bbb', '222 22 22', '200', 'b', 1, 'root 1.1', 1),
(3, 'ccc', '333 33 33', '300', 'c', 2, 'root 2.2', 2);

alter sequence hibernate_sequence restart with 10;