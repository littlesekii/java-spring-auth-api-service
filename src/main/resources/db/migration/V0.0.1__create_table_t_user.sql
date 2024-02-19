CREATE TABLE t_user
(
    id       UUID               PRIMARY KEY,
    username VARCHAR(32)        NOT NULL UNIQUE,
    password VARCHAR(60)        NOT NULL,
    role     VARCHAR(10)        NOT NULL,
    verified BOOLEAN            NOT NULL,
    active   BOOLEAN            NOT NULL DEFAULT TRUE
)