INSERT INTO member (username, name, password, nickname)
VALUES ('user1', 'User One', 'password1', 'jinugi'),
       ('user2', 'User Two', 'password2', 'jinuk');

INSERT INTO article (type, title, content, image, likes, views, recruit_status, member_id)
VALUES ('FREE', 'Title1', 'content1', 'imagefile', 0, 0, false, 1),
       ('FREE', 'Title2', 'content2', 'imagefile', 0, 0, false, 2);
