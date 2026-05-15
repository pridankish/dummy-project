create schema if not exists university;

create table university.t_university
(
    id     bigserial primary key,
    c_name varchar(200) unique not null check (length(trim(c_name)) >= 1)
);

create table university.t_group
(
    id            bigserial primary key,
    c_name        varchar(20) not null check (length(trim(c_name)) >= 1),
    university_id bigint      not null references university.t_university (id)
);