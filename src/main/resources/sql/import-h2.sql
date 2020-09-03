ALTER TABLE POST
  ALTER COLUMN create_date SET DEFAULT CURRENT_TIMESTAMP;

-- Users
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user@gmail.com', 'user', 'Name', 'Test',
   1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'blogger@gmail.com', 'blogger', 'test', 'test', 1);
-- password in plaintext: "password"


-- Roles
INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

-- User Roles
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (2, 2);


-- Posts
INSERT INTO POST (post_id, user_id, title, body, create_date,approved)
VALUES (1, 1, 'Title 1',
        '"I am not approved."',
        --         CURRENT_TIMESTAMP());
        {ts '2016-10-19 11:10:13.247'},0);
INSERT INTO POST (post_id, user_id, title, body, create_date,approved)
VALUES (2, 1, 'Title 2',
        '"I am approved."',
        --         CURRENT_TIMESTAMP(),1);
        {ts '2016-11-10 11:10:13.247'},1);
-- Comments
INSERT INTO COMMENT (post_id, user_id, body, create_date)
VALUES (1, 1,
        '"this is test comment 1."',
        CURRENT_TIMESTAMP());
INSERT INTO COMMENT (post_id, user_id, body, create_date)
VALUES (1, 2,
        '"this is test comment 2."',
        CURRENT_TIMESTAMP());
