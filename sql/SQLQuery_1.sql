

CREATE DATABASE c3331532_database;

USE c3331532_database;

CREATE LOGIN c3331532Admin
WITH PASSWORD = 'Qwerty!123';

CREATE USER c3331532Admin
FOR LOGIN c3331532Admin;

GRANT SELECT, INSERT, UPDATE, DELETE
TO c3331532Admin;

GRANT SELECT, INSERT, UPDATE, DELETE
TO dbuser;

CREATE TABLE beanStorage(
	username VARCHAR(50),
    gameBean VARBINARY(MAX)
);

INSERT INTO beanStorage VALUES('testUser', null);

CREATE TABLE testUser(
    userTest VARCHAR(50)
);

SELECT * FROM beanStorage;
