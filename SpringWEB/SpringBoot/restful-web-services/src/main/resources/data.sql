/*H2 JDBC url is jdbc:h2:mem:testdb */

insert into USER values (1, sysdate(), 'Adam');
insert into USER values (2, sysdate(), 'Eve');
insert into USER values (3, sysdate(), 'Jack');

insert into POST values (100, 'My First Post', 1);
insert into POST values (101, 'My Second Post', 1);
insert into POST values (102, 'My Third Post', 1);