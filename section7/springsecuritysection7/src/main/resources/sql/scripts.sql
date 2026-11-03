CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users
        FOREIGN KEY (username)
        REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
    ON authorities (username, authority);


INSERT IGNORE INTO `users` VALUES ('user', '{noop}EazyBytes@12345', '1');
INSERT IGNORE INTO `authorities` VALUES ('user', 'read');


//Senha: EazyBytes@54321
INSERT IGNORE INTO `users` VALUES ('admin', '{bcrypt}$2a$12$8.6upbByv0okEa7OfHFuorV29qek.sVbB9V9Q6J6dWM1bW6Qef8m', '1');
INSERT IGNORE INTO `authorities` VALUES ('admin', 'admin');

CREATE TABLE customer (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(45) NOT NULL,
    pwd VARCHAR(200) NOT NULL,
    role VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO customer(email, pwd, role) VALUES ('user@example.com', '{noop}EazyBytes@12345', 'read');
INSERT INTO customer(email, pwd, role) VALUES ('admin@example.com', '{bcrypt}$2a$12$8.6upbByv0okEa7OfHFuorV29qek.sVbB9V9Q6J6dWM1bW6Qef8m', 'admin');