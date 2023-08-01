INSERT INTO member (username, name, password, nickname)
VALUES ('user1', 'User One', 'password1', 'jinugi'),
       ('user2', 'User Two', 'password2', 'jinuk');

INSERT INTO article (type, title, content, likes, views, recruit_status, member_id)
VALUES ('FREE', 'Title1', 'content1', 0, 0, false, 1),
       ('FREE', 'Title2', 'content2', 0, 0, false, 2);

INSERT INTO article_image (image_name, image_url, article_id)
VALUES ('newjeans1.jpg', 'newjeans1.jpg', 1);


INSERT INTO article_comment (content, likes, article_id, member_id, parent_id)
VALUES ("댓글", 0, 1, 1, NULL);

INSERT INTO article_comment (content, likes, article_id, member_id, parent_id)
VALUES ("대댓글", 0, 1, 2, 1);
