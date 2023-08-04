INSERT INTO member (username, name, password, nickname)
VALUES ('user1', 'User One', 'password1', 'jinugi'),
       ('user2', 'User Two', 'password2', 'jinuk');

INSERT INTO minigame (problem, answer)
VALUES ('계란껍데기', '일반쓰레기'),
       ('치킨 뼈', '일반쓰레기'),
       ('생수병','플라스틱'),
       ('우유곽','종이');

INSERT INTO our_character (name, description, level)
VALUES ("망나뇽","짱 귀여워", 1);

INSERT INTO user_character (custom_name, experience, is_main, member_id, character_id)
VALUES ("망냐망냐",10, true, 1, 1);