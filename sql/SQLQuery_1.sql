-- DROP DATABASE c3331532_database;

CREATE DATABASE c3331532_database;

USE c3331532_database;

CREATE LOGIN c3331532Admin
WITH PASSWORD = 'Qwerty123';

CREATE USER c3331532Admin
FOR LOGIN c3331532Admin;

GRANT SELECT, INSERT, UPDATE, DELETE
TO c3331532Admin;

CREATE TABLE beanStorage(
	username VARCHAR(50),
    gameBean VARBINARY(MAX)
);

-- INSERT INTO beanStorage VALUES('testUser', null);

SELECT * FROM beanStorage;

-- DROP TABLE beanStorage;

-- SELECT * FROM beanStorage WHERE username = 'chef7';

