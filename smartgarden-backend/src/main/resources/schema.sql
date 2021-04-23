create table SETTING
(
    ID    BIGINT       not null
        primary key,
    NAME  VARCHAR(255) not null
        constraint UK_NAME
            unique,
    VALUE VARCHAR(255) not null
);
