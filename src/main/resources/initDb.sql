USE test
create table roles
(
    id   int auto_increment
        primary key,
    name varchar(45) null
);

create table users
(
    id        int auto_increment
        primary key,
    age       tinyint unsigned zerofill not null,
    name      varchar(20)               null,
    last_name varchar(255)              null,
    user_name varchar(100)              not null,
    password  varchar(100)              null,
    enabled   tinyint                   null,
    constraint users_username_uindex
        unique (user_name)
)
    charset = utf8mb4;

create table users_roles
(
    user_id int not null,
    role_id int not null,
    constraint role_fk
        foreign key (role_id) references roles (id),
    constraint user_fk
        foreign key (user_id) references users (id)
);

create index role_fk_idx
    on users_roles (role_id);

create index user_fk_idx
    on users_roles (user_id);

INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

INSERT INTO `users` (`name`,`last_name`,`user_name`,`age`, `password`, `enabled`) VALUES ('Федор','Семенов','030','semen', '$2a$10$ctM2SBSmfNOUjkQCB2AZ4eUhlPIqZ.1DT6Ds1T2xHUiTpxcOySYEq', '1');
INSERT INTO `users` (`name`,`last_name`,`user_name`,`age`, `password`, `enabled`) VALUES ('Иоанн','Волков','035','ioann@volkov.io', '$2a$10$gWd3Fyt0Fe6ljcyZQHm4U.GpxorohcYS4TcIetcDpVqchoN9FJC0O', '1');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 1);

