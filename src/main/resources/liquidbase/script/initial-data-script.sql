insert into application.app_properties values ('total.amount.limit.per.transaction', '1000', 'Total Amount Limit Per Transaction');
insert into application.app_properties values ('allow.merchant.transfer.to.merchant', 'true', 'Configuration for allow merchant able to transfer to another merchant');
insert into application.app_properties values ('new.register.user.gift.amount', '10000', 'New register user receive amount.');
insert into application.app_user (user_name, user_password) values ('kenovan', '4eY5f9vjgi8Xr1umPC0dJVGbT9E=');
insert into application.app_user (user_name, user_password) values ('seller', '4eY5f9vjgi8Xr1umPC0dJVGbT9E=');
insert into application.app_role (role_name, role_description) values ('customer', 'Customers');
insert into application.app_role (role_name, role_description) values ('merchant', 'Merchants');
insert into application.app_user_role (user_id, role_id) values ((select id from application.app_user where user_name = 'kenovan'), (select id from application.app_role where role_name = 'customer'));
insert into application.app_user_account(user_id, balance_amount) values ((select id from application.app_user where user_name = 'kenovan'), 10000);
insert into application.app_user_account_detail(account_id, transaction_type, transaction_amount, remarks) values ((select id from application.app_user_account where user_id = (select id from application.app_user where user_name = 'kenovan')), 'debit', 10000, 'New register user receive amount.');
insert into application.app_user_role (user_id, role_id) values ((select id from application.app_user where user_name = 'seller'), (select id from application.app_role where role_name = 'merchant'));
insert into application.app_user_account(user_id, balance_amount) values ((select id from application.app_user where user_name = 'seller'), 10000);
insert into application.app_user_account_detail(account_id, transaction_type, transaction_amount, remarks) values ((select id from application.app_user_account where user_id = (select id from application.app_user where user_name = 'seller')), 'debit', 10000, 'New register user receive amount.');