
create schema cloud_service;

-- Таблица для хранения информации о Пользователях User
create table cloud_service.USERS
(
    user_name VARCHAR(255) PRIMARY KEY NOT NULL,
    password  VARCHAR(255)             NOT NULL
);

-- Таблица для хранения информации о возможных Ролях Пользователей
create table cloud_service.ROLES
(
    name VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY
);

-- Таблица присвоенных ролей Пользователям
create table cloud_service.USERS_ROLES
(
    user_name VARCHAR(255) NOT NULL,
    role_name VARCHAR(50) NOT NULL ,
    PRIMARY KEY (user_name, role_name),
    FOREIGN KEY (user_name) REFERENCES cloud_service.users(user_name),
    FOREIGN KEY (role_name ) REFERENCES cloud_service.roles(name)
);

--Таблица для хранения файлов от Пользователей
create table cloud_service.FILES
(
    id             BIGSERIAL PRIMARY KEY,
    file_name      VARCHAR(255) UNIQUE NOT NULL,
    date_and_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    file_content   BYTEA                       NOT NULL,
    file_size      BIGINT                      NOT NULL,
    user_user_name VARCHAR(255)
);