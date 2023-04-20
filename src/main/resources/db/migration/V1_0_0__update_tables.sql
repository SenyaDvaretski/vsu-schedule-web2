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

CREATE TABLE "tb_role"(
    user_id UUID NOT NULL,
    role VARCHAR(40)
)