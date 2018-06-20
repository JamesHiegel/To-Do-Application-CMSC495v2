create sequence users_seq as int start with 1 increment by 1;
create table users (
  us_id integer primary key,
  us_userName varchar(50) not null,
  us_password varchar(50) not null
);
insert into users values (next value for users_seq, 'sdevadmin', 'sdevadmin'); -- This will eventually be an encrypted md5hash

create table task_type (
  tt_id integer primary key,
  tt_description varchar(20) not null
);
insert into task_type values (1,'Personal');
insert into task_type values (2,'Professional');

create table priority (
 pr_id integer primary key,
 pr_description varchar(20) not null
);
insert into priority values (1, 'High');
insert into priority values (2, 'Medium');
insert into priority values (3, 'Low');

create sequence task_seq as int start with 1 increment by 1;
create table task (
  ts_id integer Primary Key,
  ts_user_id integer not null references users(us_id),
  ts_type integer not null references task_type(tt_id),
  ts_priority integer not null references priority(pr_id),
  ts_date date not null,
  ts_description varchar(255) not null
);
insert into task values (next value for task_seq, 1, 1, 1, '06/01/2018', 'Personal High Priority Task' );
insert into task values (next value for task_seq, 1, 1, 2, '06/01/2018', 'Personal Medium Priority Task' );
insert into task values (next value for task_seq, 1, 1, 3, '06/01/2018', 'Personal Low Priority Task' );
insert into task values (next value for task_seq, 1, 1, 1, '06/01/2018', 'Professional High Priority Task' );
insert into task values (next value for task_seq, 1, 1, 2, '06/01/2018', 'Professional Medium Priority Task' );
insert into task values (next value for task_seq, 1, 1, 3, '06/01/2018', 'Professional Low Priority Task' );

select * from users;
select * from task_type;
select * from priority;
select * from task;

