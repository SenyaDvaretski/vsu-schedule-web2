CREATE TABLE "app_user"(
    user_id UUID NOT NULL,
    created_at DATE,
    login varchar(40),
    lastname varchar(40),
    name varchar(40),
    email varchar(40),
    password varchar(1000),
    status varchar(20)

);

CREATE TABLE "token"(
    token_id UUID PRIMARY KEY NOT NULL,
    token varchar(300),
    token_type varchar(20),
    revoked boolean,
    expired boolean,
    user_id UUID

);

CREATE TABLE "tb_role"(
    user_id UUID NOT NULL,
    role VARCHAR(40)
);

CREATE TABLE "groups"(
    group_id VARCHAR(40) PRIMARY KEY NOT NULL,
    group_name VARCHAR(40) NOT NULL
);

CREATE TABLE "teachers"(
    teacher_id integer PRIMARY KEY NOT NULL,
    firstname varchar(40)  not null,
    lastname varchar(40) not null,
    surname varchar(40) not null,
    initials varchar(3) not null,
    qualification varchar(40),
    fullname varchar(40),
    img_link varchar(100),
    description varchar(150)
);

CREATE TABLE "lessons"(
    lesson_id UUID primary key not null,
    start_time varchar(10),
    end_time varchar(10),
    auditorium varchar(10),
    "date" varchar(15),
    weekday varchar(15),
    lesson_name varchar(30),
    type varchar(20),
    subgroup_id varchar(40),
    group_id varchar(40)
);

CREATE TABLE "subgroups"(
    subgroups_id varchar(40),
    group_id varchar(40)
);

CREATE TABLE "lessons_teachers"(
    teachers_teacher_id integer,
    lessons_lesson_id UUID,
    CONSTRAINT id PRIMARY KEY (teachers_teacher_id,lessons_lesson_id)

);