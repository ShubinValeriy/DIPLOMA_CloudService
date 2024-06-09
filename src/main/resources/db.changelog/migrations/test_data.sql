INSERT INTO cloud_service.roles(name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO cloud_service.users(user_name, password)
VALUES ('user', '$2a$10$4Pw2BG0CDKN3Hd8nmoB7Q.Rx5/lpex6xpf9EUeorMDVS3IhH3PBP6'),
       ('admin', '$2a$10$xcsmuDO.f8lDUhaJ9BhWW.q6sQlzzukBgTEzDBEO13SAU6wA4S7yS');

INSERT INTO cloud_service.users_roles(user_name, role_name)
VALUES ('user', 'ROLE_USER'),
       ('admin', 'ROLE_ADMIN');
