create table university.t_lesson
(
    id                  bigserial primary key,
    c_auditory_number   varchar(20) not null check (length(trim(c_auditory_number)) >= 1),
    c_lesson_type       varchar(20) not null check (length(trim(c_lesson_type)) >= 1),
    c_week_type         varchar(10) not null check (length(trim(c_lesson_type)) >= 1),
    c_week_day          varchar(10) not null check (length(trim(c_lesson_type)) >= 1),
    c_lesson_start_time timestamp,
    group_id            bigint      not null references university.t_group (id)
);