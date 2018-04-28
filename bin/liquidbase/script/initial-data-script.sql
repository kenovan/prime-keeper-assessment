insert into application.app_properties values ('test key', 'test value', 'testing data');
insert into application.app_user (user_name, user_password) values ('kenovan', 'abcd1234');
insert into application.app_role (role_name, role_description) values ('customer', 'Customers');
insert into application.app_role (role_name, role_description) values ('merchant', 'Merchants');
insert into application.app_user_role (user_id, role_id) values ((select id from application.app_user where user_name = 'kenovan'), (select id from application.app_role where role_name = 'customer'));