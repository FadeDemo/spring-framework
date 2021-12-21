create table user (
    id int(11) not null auto_increment,
    name varchar(255) default null,
    age int(11) default null,
    sex varchar(255) default null,
    primary key (id)
) engine = InnoDB default charset = utf8;