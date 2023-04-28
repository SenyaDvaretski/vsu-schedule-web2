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
    token_id UUID,
    token varchar(300),
    token_type varchar(20),
    revoked boolean,
    expired boolean,
    user_id UUID

);

CREATE TABLE "tb_role"(
    user_id UUID NOT NULL,
    role VARCHAR(40)
)