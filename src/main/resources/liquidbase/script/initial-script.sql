create table application.app_properties(
	app_key varchar(150) primary key COMMENT 'Properties Keys',
	app_value varchar(250) COMMENT 'Properties Values',
	description varchar(250) COMMENT 'Properties Description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table application.app_properties COMMENT = 'Application properties list';

create table application.app_user(
	id int auto_increment primary key COMMENT 'User ID',
	user_name varchar(100) not null COMMENT 'User Name',
	user_password varchar(200) not null COMMENT 'Encrypted Password',
	create_date datetime default current_timestamp COMMENT 'Register date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx1_app_user
ON application.app_user (user_name);

alter table application.app_user COMMENT = 'User Table';

create table application.app_role(
	id int auto_increment primary key COMMENT 'Role ID',
	role_name varchar(100) not null COMMENT 'Role Name',
	role_description varchar(300) COMMENT 'Role Description',
	create_date datetime default current_timestamp COMMENT 'Register date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx1_app_role
ON application.app_role (role_name);

alter table application.app_role COMMENT = 'Role Table';

create table application.app_user_role(
	user_id int not null COMMENT 'Refer to app_user table id',
    role_id int not null COMMENT 'Refer to app_role table id',
    create_date datetime default current_timestamp COMMENT 'Record Created Date',
	PRIMARY KEY (user_id, role_id),
	FOREIGN KEY(user_id) REFERENCES application.app_user(id),
    FOREIGN KEY(role_id) REFERENCES application.app_role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table application.app_role COMMENT = 'User Role Table(Join table from app_user and app_role)';

create table application.audit_login(
	id int auto_increment primary key COMMENT 'record id',
    login_input_name varchar(300) not null COMMENT 'user input name',
    login_input_password varchar(400) not null COMMENT 'user input password',
    login_status varchar(50) not null COMMENT 'is user login status, success or fail',
    login_ip_address varchar(30) COMMENT 'user IP address',
    login_user_agent longtext COMMENT 'user agent',
    remarks varchar(100) COMMENT 'remarks for login record',
    create_date datetime default current_timestamp COMMENT 'Record Created Date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX idx1_audit_login
ON application.audit_login (login_status);

CREATE INDEX idx2_audit_login
ON application.audit_login (login_ip_address);

CREATE INDEX idx3_audit_login
ON application.audit_login (login_input_name);

CREATE INDEX idx4_audit_login
ON application.audit_login (create_date);

alter table application.audit_login COMMENT = 'User Login Activities Audit Table';

create table application.app_user_login(
	id int auto_increment primary key COMMENT 'record id',
	user_id int not null COMMENT 'Refer to app_user table id',
    user_session varchar(400) not null COMMENT 'user session id',
    user_token varchar(200) not null COMMENT 'user token',
    token_expired datetime not null COMMENT 'uesr token expired date time',
    create_date datetime default current_timestamp COMMENT 'Record Created Date',
    FOREIGN KEY(user_id) REFERENCES application.app_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX idx1_app_user_login
ON application.app_user_login (user_id, user_session);

alter table application.app_user_login COMMENT = 'User Login Table';