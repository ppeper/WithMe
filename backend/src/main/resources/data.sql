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

INSERT INTO article (article_id, type, title, content, views, recruit_status, url_title, url, member_id) VALUES (1, 'FREE', '더미 데이터 제목 1', '더미 데이터 내용 1', 0, false, '더미 URL 제목 1', 'http://example.com/1', 1);
INSERT INTO article (article_id, type, title, content, views, recruit_status, url_title, url, member_id) VALUES (2, 'FREE', '더미 데이터 제목 2', '더미 데이터 내용 2', 0, false, '더미 URL 제목 2', 'http://example.com/2', 1);
INSERT INTO article (article_id, type, title, content, views, recruit_status, url_title, url, member_id) VALUES (3, 'TOGETHER', '더미 데이터 제목 3', '더미 데이터 내용 3', 0, false, '더미 URL 제목 3', 'http://example.com/3', 1);
INSERT INTO article (article_id, type, title, content, views, recruit_status, url_title, url, member_id) VALUES (4, 'TOGETHER', '더미 데이터 제목 4', '더미 데이터 내용 4', 0, false, '더미 URL 제목 4', 'http://example.com/4', 1);
INSERT INTO article (article_id, type, title, content, views, recruit_status, url_title, url, member_id) VALUES (5, 'TOGETHER', '더미 데이터 제목 5', '더미 데이터 내용 5', 0, false, '더미 URL 제목 5', 'http://example.com/5', 1);

INSERT INTO article_comment (content, article_id, member_id)
VALUES ('부모 댓글입니다.', 1, 1);

INSERT INTO article_comment (content, article_id, member_id, parent_comment_id)
VALUES ('자식 댓글1입니다.', 1, 1, 1);

INSERT INTO article_comment (content, article_id, member_id, parent_comment_id)
VALUES ('자식 댓글2입니다.', 1, 1, 1);

INSERT INTO article_comment (content, article_id, member_id, parent_comment_id)
VALUES ('자식 댓글1의 대댓글입니다.', 1, 1, 2);

INSERT INTO article_comment (content, article_id, member_id)
VALUES ('독립적인 댓글입니다.', 1, 1);

INSERT INTO article_comment_like (article_id, member_id, article_comment_id)
VALUES (1, 1, 1);

INSERT INTO article_comment_like (article_id, member_id, article_comment_id)
VALUES (1, 1, 1);

INSERT INTO article_comment_like (article_id, member_id, article_comment_id)
VALUES (1, 2, 2);

INSERT INTO article_comment_like (article_id, member_id, article_comment_id)
VALUES (1, 2, 3);

INSERT INTO article_comment_like (article_id, member_id, article_comment_id)
VALUES (1, 2, 4);


INSERT INTO article_like (article_id, member_id)
VALUES (1, 1);

INSERT INTO article_like (article_id, member_id)
VALUES (1, 2);

INSERT INTO article_like (article_id, member_id)
VALUES (2, 1);

INSERT INTO article_like (article_id, member_id)
VALUES (3, 2);

INSERT INTO article_like (article_id, member_id)
VALUES (4, 2);

INSERT INTO location (location_name, latitude, longitude)
VALUES ('서울특별시 송파구', 37.49650739457211, 127.0868362387235);

INSERT INTO location (location_name, latitude, longitude)
VALUES ('경기도 수원시', 37.25748884382484, 127.03120068818286);

INSERT INTO location (location_name, latitude, longitude)
VALUES ('부산광역시 해운대구', 35.17440133141441, 129.2071257645525);

INSERT INTO location (location_name, latitude, longitude)
VALUES ('대전광역시 유성구', 36.38219930109041, 127.34781678051049);

INSERT INTO location (location_name, latitude, longitude)
VALUES ('인천광역시 연수구', 37.41059245415787, 126.68408651292456);

INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
VALUES ('서울 송파구 쓰레기 많음', '서울 송파구 어디어디에 쓰레기가 많이 더러워져 있습니다. 청소 바랍니다', 10, 37.49650739457211, 127.0868362387235, false, 1, 1);

INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
VALUES ('수원시 쓰레기 무담장 실화냐?', '수원시에 어디어디에 쓰레기를 쌓았더니 처음엔 나쁜 냄새만 나더니 이젠 악취가 전체 수원시를 울린다고 합니다! 조속한 처리바랍니다.', 0, 37.25748884382484, 127.03120068818286, false, 2, 2);

INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
VALUES ('해운대구 해수욕장 쓰레기', '해운대구 해수욕장에서 쓰레기를 쌓아두고 가는 사람들이 많습니다. 처리 부탁드립니다.', 5, 35.17440133141441, 129.2071257645525, false, 1, 3);

INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
VALUES ('대전 유성구 하남중학교 판자 쓰레기', '대전 유성구 하남중학교 앞에 판자쓰레기가 너무 많이 쌓여있습니다. 이곳을 지나가는 차량들이 너무나도 조심스러워합니다. 처리바랍니다.', 0, 36.38219930109041, 127.34781678051049, false, 2, 4);

INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
VALUES ('인천 연수구 아웃백 쓰레기 무단투기 확인', '인천 연수구 아웃백에서 먹고나온 인분들의 쓰레기 무단투기를 확인했습니다. 처리바랍니다.', 2, 37.41059245415787, 126.68408651292456, false, 2, 5);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('정말 현타옵니다. 우리 동네도 너무 쓰레기들로 가득 차 있어서 이번 기회에 청소를 해주셨으면 좋겠습니다.', 1, 1, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('저도 그 생각입니다. 세상을 지키기 위한 가장 작은 시작이 우리 동네부터 시작하는것인듯 합니다.', 1, 2, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('실화인가요? 층고도 넘어가면서 처리못한 쓰레기라니..', 2, 1, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('악취보다 페코페이...ㅠ', 2, 2, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('이번 여름은 해수욕장 가지 마세요..', 3, 2, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('이쪽 지역 살아서 좋은게 몇 안남네요. 해운대까지 이런 상황이면 다시방문하는 관광객도 줄일것 같아서 제발 조속한 처리바랍니다', 3, 1, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('진짜 넘나 위험한 상황이네요..  그도 그럴것이 판자쓰레기 속에 누군가 단련된 이미지를 가지고 등장한다면 안전한 도시라는 평가를 받을수 없겠죠', 4, 2, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('대전 실화냐? 대전에서 또 쓰레기 문제라니 참 억울해요.', 4, 1, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('아웃백 갔을때 쓰레기통에 쓰레기 넣어놓고 가면 알아서 청소하겠죠?하고 생각하지마세요.', 5, 1, null);

INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
VALUES ('네 그렇죠. 우리 모두 쓰레기문제에 대한 인식을 바꿔야할 필요가 있습니다.', 5, 2, null);

INSERT INTO report_like (report_id, member_id)
VALUES (1, 1);

INSERT INTO report_like (report_id, member_id)
VALUES (1, 2);

INSERT INTO report_like (report_id, member_id)
VALUES (2, 1);

INSERT INTO report_like (report_id, member_id)
VALUES (2, 2);

INSERT INTO report_like (report_id, member_id)
VALUES (3, 2);
