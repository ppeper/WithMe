INSERT INTO member (username, name, password, nickname)
VALUES ('user1', 'User One', 'password1', 'jinugi'),
       ('user2', 'User Two', 'password2', 'jinuk');

INSERT INTO minigame (problem, answer)
VALUES ('계란껍데기', '일반쓰레기'),
       ('치킨 뼈', '일반쓰레기'),
       ('생수병','플라스틱'),
       ('유리화분','유리'),
       ('종이박스','종이'),
       ('코팅된 전단지','일반쓰레기'),
       ('책','종이');

INSERT INTO our_character (name, char_ord_id, description, level)
VALUES ('암모나이트',1,'암모나이트는 옛날에 존재했던 조개류의 생물이에요. 암모나이트는 오랜시간동안 지구의 압력과 온도 변화에도 버틸 수 있을정도로 껍질이 매우 튼튼합니다.', 'LEVEL_1'),
       ('벨루가(흰 돌고래)',2,'몸이 둥글고 하얀 몸과 멜론같이 생긴 머리를 가지고 있어서 melonhead라고도 해요. 벨루가는 사람들의 무분별한 포획이나 오염으로 멸종위기종이 되었으며, 벨루가가 살아갈 수 있게 우리가 지켜줘야해요.','LEVEL_1'),
       ('해마',3,'말과 유사한 머리를 가지고 있어서 해마라는 이름이 붙었어요. 수컷이 출산을 하는걸로 유명해요. 산호초들도 줄어들어 해마의 서식지가 줄어들고 21개국에 걸쳐 연간 약 3700만 마리가 죽고 있다고 해요', 'LEVEL_1'),
        ('범고래',4,'포유류로 높은 지능과 뛰어난 신체능력, 무리지어다니는 습성으로 생태계 피라미드 최상위에 있는 생물이에요. 사람을 좋아하고 친근하게 다가가는 친절한 친구랍니다', 'LEVEL_1'),
       ('니모',5,'이 친구의 정확한 이름은 "흰동가리"에요. 영화 "니모를 찾아서"가 개봉한 이후 전 세계적으로 애완용으로 기르려는 사람들이 늘어서 흰동가리를 난폭하게 포획하고 환경파괴로 인해 서식지가 줄어들고 있어요', 'LEVEL_1'),
       ('해달',6,'만화영화 보노보노의 모티브가 된 친구에요. 수달의 일종으로, 몸에 주머니가 있어서 돌멩이를 지니고 다니면서 오랫동안 사용해요. 다른 수달들과 달리 대부분을 물에서 활동해요. 모피의 질이 좋아 사람들이 포획하기 때문에 우리 친구 해달을 지켜줘야 해요', 'LEVEL_1'),
       ('바다사자',7,'등과 배 부분은 황갈색과 어두운 갈색을 띠고 있는데, 물에 젖으면 회백색으로 보여 가끔 물개와 혼동되기도 해요. 한반도의 동해해안과 일본 열도 해안에 서식했지만, 현재는 멸종했어요.', 'LEVEL_1'),
       ('갈매기',8,'바다에 가면 많이 보이는 새에요. 물새라서 물에 둥둥뜨는 것도 가능하고, 발에 물갈퀴도 달려있어요. 겨울에는 머리에 갈색줄무늬가 생기기도 하는 신기한 친구랍니다', 'LEVEL_1'),
       ('새우',9,'단단하고 뻣뻣한 갑각으로 싸여져있고 여러개의 몸마디로 되어있어요. 평소에는 앞으로 헤엄치지만 지느러미처럼 생긴 꼬리채를 재빨리 휘둘러 뒤로 도망칠 수도 있어요', 'LEVEL_1'),
       ('거북',10,'파충류에 속하는 알을 낳는 동물이에요. 몸은 단단한 등딱지안에 있어요. 폐호흡을 하기 때문에 숨을 쉬기 위해 바다 위로 올라오기도 해요. 최근엔 헤엄치다 쓰레기로 고통받는 거북이들도 많이 보이니, 우리가 지켜줘야해요', 'LEVEL_1'),
       ('고래',11,'고래는 많은 종류가 있는데, 많은 양의 이산화탄소를 흡수해서 고래 한마리가 수천그루의 나무와 비슷한 효과를 내는 고마운 친구에요. 거대한 몸집을 가지고 있고, 폐로 호흡하기 때문에 규칙적으로 물표면으로 올라와 숨을 쉬어야해요', 'LEVEL_1'),
       ('고래',11,'고래는 많은 종류가 있는데, 많은 양의 이산화탄소를 흡수해서 고래 한마리가 수천그루의 나무와 비슷한 효과를 내는 고마운 친구에요. 거대한 몸집을 가지고 있고, 폐로 호흡하기 때문에 규칙적으로 물표면으로 올라와 숨을 쉬어야해요', 'LEVEL_2'),
       ('고래',11,'고래는 많은 종류가 있는데, 많은 양의 이산화탄소를 흡수해서 고래 한마리가 수천그루의 나무와 비슷한 효과를 내는 고마운 친구에요. 거대한 몸집을 가지고 있고, 폐로 호흡하기 때문에 규칙적으로 물표면으로 올라와 숨을 쉬어야해요', 'LEVEL_3'),
       ('돌고래',12,'고래목에 속한 동물 중 길이가 작은 이빨고래소목에 속하는 해양포유류에요. 초음파를 이용해서 세상을 보고, 청각이 매우 좋아요. 굉장히 발달한 뇌를 가지고 있어서 똑똑한 친구에요', 'LEVEL_1'),
       ('돌고래',12,'고래목에 속한 동물 중 길이가 작은 이빨고래소목에 속하는 해양포유류에요. 초음파를 이용해서 세상을 보고, 청각이 매우 좋아요. 굉장히 발달한 뇌를 가지고 있어서 똑똑한 친구에요', 'LEVEL_2'),
       ('돌고래',12,'고래목에 속한 동물 중 길이가 작은 이빨고래소목에 속하는 해양포유류에요. 초음파를 이용해서 세상을 보고, 청각이 매우 좋아요. 굉장히 발달한 뇌를 가지고 있어서 똑똑한 친구에요', 'LEVEL_3'),
       ('물범',13,'물범과에 속하는 포유류이고, 바다표범과 중에서 가장 작은 동물이에요. 몸색깔은 일반적으로 옅은 은회색이고, 우리나라 해역에 출현하고, 특히 백령도 근처에서 잘 나타나는 친구에요. 멸종위기 야생동물이라 우리가 지켜줘야해요', 'LEVEL_1'),
       ('물범',13,'물범과에 속하는 포유류이고, 바다표범과 중에서 가장 작은 동물이에요. 몸색깔은 일반적으로 옅은 은회색이고, 우리나라 해역에 출현하고, 특히 백령도 근처에서 잘 나타나는 친구에요. 멸종위기 야생동물이라 우리가 지켜줘야해요', 'LEVEL_2'),
       ('물범',13,'물범과에 속하는 포유류이고, 바다표범과 중에서 가장 작은 동물이에요. 몸색깔은 일반적으로 옅은 은회색이고, 우리나라 해역에 출현하고, 특히 백령도 근처에서 잘 나타나는 친구에요. 멸종위기 야생동물이라 우리가 지켜줘야해요', 'LEVEL_3');


-- INSERT INTO user_character (custom_name, experience, main, member_id, character_id, location_name)
-- VALUES ('망냐망냐',10, true, 1, 1, '경기도 수원시'),
--        ('망냐2',10, false, 1, 2,'경기도 수원시'),
--         ('망냐2',10, false, 1, 3,'경기도 수원시'),
--         ('망냐2',10, false, 1, 4,'경기도 수원시');


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

INSERT INTO quiz_problem (problem, answer, commentary)
VALUES ('퀴즈2', '1','퀴즈2답');

INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('플라스틱 쓰레기',2);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('유기적 노출물',2);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('화학 물질 누출',2);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('금속 오염',2);

INSERT INTO quiz_problem (problem, answer, commentary)
VALUES ('퀴즈3', '1','퀴즈3답');

INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('플라스틱 쓰레기',3);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('유기적 노출물',3);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('화학 물질 누출',3);
INSERT INTO quiz_problem_choice (content, quizproblem_id)
VALUES ('금속 오염',3);


INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('해변에서 작은 플라스틱 조각들을 주울 때, 어떤 일을 할 수 있을까요? (O/X)', 'O','해변에서 작은 플라스틱 조각들을 주우는 것은 매우 중요한 일입니다. 작은 플라스틱 조각들은 해양 생태계에 해를 끼칠 수 있으며 해양 동물들에게 위험을 초래할 수 있습니다. 따라서 우리는 해변을 깨끗하게 유지하기 위해 플라스틱 조각들을 주워서 버려야 합니다. 이렇게 함으로써 우리는 작은 노력으로도 바다의 건강을 지킬 수 있습니다.');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('ox문제1', 'O','답1');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('ox문제2', 'O','답2');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('ox문제3', 'O','답3');



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

INSERT INTO location (name, center_latitude, center_longitude, left_latitude, left_longitude, right_latitude, right_longitude)
VALUES ('서울특별시 송파구', 37.49650739457211, 127.0868362387235, 37.4965050000, 127.0868000000, 37.496510000, 127.0868400000);

INSERT INTO location (name, center_latitude, center_longitude, left_latitude, left_longitude, right_latitude, right_longitude)
VALUES ('경기도 수원시', 37.25748884382484, 127.03120068818286, 37.25718884382484, 127.0309068818286, 37.25754884382484, 127.0369068818286);

-- INSERT INTO location (location_name, latitude, longitude)
-- VALUES ('부산광역시 해운대구', 35.17440133141441, 129.2071257645525);
--
-- INSERT INTO location (location_name, latitude, longitude)
-- VALUES ('대전광역시 유성구', 36.38219930109041, 127.34781678051049);
--
-- INSERT INTO location (location_name, latitude, longitude)
-- VALUES ('인천광역시 연수구', 37.41059245415787, 126.68408651292456);
--
-- INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
-- VALUES ('서울 송파구 쓰레기 많음', '서울 송파구 어디어디에 쓰레기가 많이 더러워져 있습니다. 청소 바랍니다', 10, 37.49650739457211, 127.0868362387235, false, 1, 1);
--
-- INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
-- VALUES ('수원시 쓰레기 무담장 실화냐?', '수원시에 어디어디에 쓰레기를 쌓았더니 처음엔 나쁜 냄새만 나더니 이젠 악취가 전체 수원시를 울린다고 합니다! 조속한 처리바랍니다.', 0, 37.25748884382484, 127.03120068818286, false, 2, 2);
--
-- INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
-- VALUES ('해운대구 해수욕장 쓰레기', '해운대구 해수욕장에서 쓰레기를 쌓아두고 가는 사람들이 많습니다. 처리 부탁드립니다.', 5, 35.17440133141441, 129.2071257645525, false, 1, 3);
--
-- INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
-- VALUES ('대전 유성구 하남중학교 판자 쓰레기', '대전 유성구 하남중학교 앞에 판자쓰레기가 너무 많이 쌓여있습니다. 이곳을 지나가는 차량들이 너무나도 조심스러워합니다. 처리바랍니다.', 0, 36.38219930109041, 127.34781678051049, false, 2, 4);
--
-- INSERT INTO report (title, content, views, latitude, longitude, admin_confirm_status, member_id, location_id)
-- VALUES ('인천 연수구 아웃백 쓰레기 무단투기 확인', '인천 연수구 아웃백에서 먹고나온 인분들의 쓰레기 무단투기를 확인했습니다. 처리바랍니다.', 2, 37.41059245415787, 126.68408651292456, false, 2, 5);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('정말 현타옵니다. 우리 동네도 너무 쓰레기들로 가득 차 있어서 이번 기회에 청소를 해주셨으면 좋겠습니다.', 1, 1, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('저도 그 생각입니다. 세상을 지키기 위한 가장 작은 시작이 우리 동네부터 시작하는것인듯 합니다.', 1, 2, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('실화인가요? 층고도 넘어가면서 처리못한 쓰레기라니..', 2, 1, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('악취보다 페코페이...ㅠ', 2, 2, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('이번 여름은 해수욕장 가지 마세요..', 3, 2, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('이쪽 지역 살아서 좋은게 몇 안남네요. 해운대까지 이런 상황이면 다시방문하는 관광객도 줄일것 같아서 제발 조속한 처리바랍니다', 3, 1, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('진짜 넘나 위험한 상황이네요..  그도 그럴것이 판자쓰레기 속에 누군가 단련된 이미지를 가지고 등장한다면 안전한 도시라는 평가를 받을수 없겠죠', 4, 2, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('대전 실화냐? 대전에서 또 쓰레기 문제라니 참 억울해요.', 4, 1, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('아웃백 갔을때 쓰레기통에 쓰레기 넣어놓고 가면 알아서 청소하겠죠?하고 생각하지마세요.', 5, 1, null);
--
-- INSERT INTO report_comment (content, report_id, member_id, parent_comment_id)
-- VALUES ('네 그렇죠. 우리 모두 쓰레기문제에 대한 인식을 바꿔야할 필요가 있습니다.', 5, 2, null);
--
-- INSERT INTO report_like (report_id, member_id)
-- VALUES (1, 1);
--
-- INSERT INTO report_like (report_id, member_id)
-- VALUES (1, 2);
--
-- INSERT INTO report_like (report_id, member_id)
-- VALUES (2, 1);
--
-- INSERT INTO report_like (report_id, member_id)
-- VALUES (2, 2);
--
-- INSERT INTO report_like (report_id, member_id)
-- VALUES (3, 2);
INSERT INTO location_our_character (location_id, our_character_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4);