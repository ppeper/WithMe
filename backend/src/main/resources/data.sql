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

INSERT INTO quiz_problem (problem, answer, commentary)
VALUES ('어떤 폐기물이 해양 동물에 가장 위험한 영향을 미칠까요?', '1','플라스틱 쓰레기는 해양 동물에 심각한 피해를 주며 해양 환경 오염의 주요 원인 중 하나입니다. 어린이들은 플라스틱 사용 줄이기와 재활용을 통해 이 문제를 함께 해결할 수 있습니다.');

INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('플라스틱 쓰레기',1);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('유기적 노출물',1);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('화학 물질 누출',1);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('금속 오염',1);

INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('해변에서 작은 플라스틱 조각들을 주울 때, 어떤 일을 할 수 있을까요? (O/X)', 'O','해변에서 작은 플라스틱 조각들을 주우는 것은 매우 중요한 일입니다. 작은 플라스틱 조각들은 해양 생태계에 해를 끼칠 수 있으며 해양 동물들에게 위험을 초래할 수 있습니다. 따라서 우리는 해변을 깨끗하게 유지하기 위해 플라스틱 조각들을 주워서 버려야 합니다. 이렇게 함으로써 우리는 작은 노력으로도 바다의 건강을 지킬 수 있습니다.');
