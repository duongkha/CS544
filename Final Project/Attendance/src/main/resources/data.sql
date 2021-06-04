INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('FACULTY');
INSERT INTO roles (name) VALUES ('STUDENT');

-- password is the same as username
INSERT INTO USERS(username,password,first_name,last_name,enabled)
VALUES('admin','$2a$10$JirblkU.nrFQVWpRJkeTcev6J1I/UoZvIDdjPUiIHKAeBrtZq8iYe','Admin','Admin',1);

INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT USER_ID FROM USERS WHERE USERNAME='ADMIN' LIMIT 1) , (SELECT ROLE_ID FROM ROLES WHERE NAME='ADMIN' LIMIT 1);
