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
VALUES ('펭귄',1,'펭귄은 물 에서도 뛰어난 수영 실력을 가진 멋진 조류입니다. 특히 남극과 주변 지역에서 주로 발견되는데, 추운 환경에서도 귀여운 모습으로 사람들의 관심을 사고 있답니다', 'LEVEL_1'),
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
VALUES ('물고기들은 플라스틱 쓰레기를 먹어도 안전하다', 'X','플라스틱 쓰레기는 바다 생물에게 위험한 물질입니다. 많은 물고기들이 실수로 플라스틱을 먹으면, 물고기 몸에 해로운 화학 물질이 쌓일 수 있어요');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('바다 거북은 플라스틱 가방을 해파리로 착각할 때가 있다', 'O','바다 거북들은 종종 떠 돌아다니는 플라스틱 가방을 해파리로 오해하고 먹습니다. 그러면, 거북이들은 소화기관에 문제가 생겨 건강에 심각한 위기가 올 수 있어요');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('해양 동물들은 소리 때문에 방해를 받지 않는다', 'X','선박이나 수중 공사와 같은 인간의 활동으로 인해 발생하는 소음은 해양 동물들의 행동에 영향을 미칩니다. 특히 고래와 돌고래와 같은 해양 포유류들이 인간의 소음 때문에 스트레스를 받거나 의사소통에 문제가 생길 수 있으니, 조심해야 해요');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('지구온난화로 인한 바다염도와 온도 변화는 해양환경과 생물체들에 영향을 주지 않는다', 'X','바다의 염도와 온도 변화는 해양생물들에게 큰 영향을 미칩니다. 특히 급격한 변화는 일부 해양 생물들이 적응하기 어렵게 만들어 생존에 큰 위협이 되니, 환경오염으로 바다의 온도를 지켜줘야 해요');
INSERT INTO ox_quiz_problem (problem, answer, commentary)
VALUES ('바이오 플라스틱은 모두 해양환경에서 분해될 수 있다', 'X','바다는 자연치유능력이 있어서 일정수준 스스로 정화될 수 있지만, 모든 바이오 플라스틱이 실제로 항상 자연 분해되지는 않습니다. 일부 바이오 플라스틱은 오직 고온의 산업용 분해 시설에서만 안전하게 처리될 수 있어요');



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

INSERT INTO location_our_character (location_id, our_character_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10),
    (1, 11),
    (1, 14),
    (1, 17),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9),
    (2, 10),
    (2, 11),
    (2, 14),
    (2, 17),
    (3, 1),
    (3, 2),
    (3, 3),
    (3, 4),
    (3, 5),
    (3, 6),
    (3, 7),
    (3, 8),
    (3, 9),
    (3, 10),
    (3, 11),
    (3, 14),
    (3, 17),
    (4, 1),
    (4, 2),
    (4, 3),
    (4, 4),
    (4, 5),
    (4, 6),
    (4, 7),
    (4, 8),
    (4, 9),
    (4, 10),
    (4, 11),
    (4, 14),
    (4, 17),
    (5, 1),
    (5, 2),
    (5, 3),
    (5, 4),
    (5, 5),
    (5, 6),
    (5, 7),
    (5, 8),
    (5, 9),
    (5, 10),
    (5, 11),
    (5, 14),
    (5, 17);


INSERT INTO location (name, center_latitude, center_longitude, left_latitude, left_longitude, right_latitude, right_longitude)
VALUES
    ('부산 광안리 해수욕장', '35.153387', '129.113506', '35.153518', '129.115095', '35.154469', '129.123013'),    ('충남 서천 춘장대 해수욕장', '36.016374', '126.700953', '36.0109', '126.6983', '36.0226', '126.7037'),
    ('울산 일산 해수욕장', '35.431840', '129.371506', '35.4274', '129.3644', '35.4374', '129.381'),
    ('여수 웅천천수공원', '34.746716', '127.73199', '34.7450', '127.7275', '34.7482', '127.7363'),
    ('구미 인동', '35.861214', '128.597280','36.1049044','128.4199745','36.100237','128.4241094');

iNSERT INTO campaign (campaign_id, authority, completion_status, end_date, name, start_date, location_id)
VALUES
    ('홍길동 연구소', 0, '2022-02-01', '캠페인1','2022-01-01',1),
    ('한국환경보호 원', 1, '2022-03-01', '캠페인2','2022-02-01',2),
    ('집단장애 인식 캠페인', 0, '2022-04-01', '캠페인3','2022-03-01',3),
    ('지구촌환경보호단체', 1, '2022-05-01', '캠페인4','2022-04-01',4),
    ('강남구청', 1, '2022-06-01', '캠페인5','2022-05-01',5);