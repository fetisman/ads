delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true , '$2a$08$/.9mD/8fu5nkwSzmT1/5HOIoFMzBOCiOpqDDihZwUUTMxTiRjLqqK', 'ff101'),
(2, true , '$2a$08$uFL0XzEZzg.qhMdBqUh4LeMyet1y8cf86N65WufrFiT31byEhRyfq', 'ff102');

insert into user_role(user_id, roles) values
(1, 'USER') , (1, 'ADMIN') ,
(2, 'USER');