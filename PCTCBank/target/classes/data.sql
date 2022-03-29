create table client (
id serial primary key, full_name varchar(30));

create table account(id serial primary key, account_number varchar, account_balance numeric, 
client_id integer, constraint fk_client foreign key (client_id) references client(id) on delete cascade);

insert into client(full_name) values ('Spongebob Squarepants');
insert into client(full_name) values ('Patrick Star');
insert into client(full_name) values ('Squidward Tentacles');


insert into account(account_number, account_balance, client_id) values ('A01', 300, 1);
insert into account(account_number, account_balance, client_id) values ('A02', 400, 2);
insert into account(account_number, account_balance, client_id) values ('A03', 500, 3);
insert into account(account_number, account_balance, client_id) values ('B01', 7000,1);

-- create three more of each client and accounts 
insert into client(full_name) values ('Pearl Krabs');
insert into client(full_name) values ('Sandy Cheeks');
insert into client(full_name) values ('Bubble Bass');
insert into account(account_number, account_balance, client_id) values ('A04', 300, 4);
insert into account(account_number, account_balance, client_id) values ('A05', 400, 5);
insert into account(account_number, account_balance, client_id) values ('A06', 500, 6);
insert into account(account_number, account_balance, client_id) values ('B04', 7000, 4);


drop table client;
drop table account;