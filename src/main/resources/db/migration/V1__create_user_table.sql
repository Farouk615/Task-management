CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255),
    password     VARCHAR(255),
    role         VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255)
);