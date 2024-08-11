use woomsDB;
set foreign_key_checks = 0;
############################### 그룹 데이터 삽입 ###############################
-- 'ssafy@ssafy.com' 사용자 UUID
SET @uuid1 = (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com');

-- 'example6@example.com' 사용자 UUID
SET @uuid2 = (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com');

-- 'example18@example.com' 사용자 UUID
SET @uuid3 = (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com');

-- 'example28@example.com' 사용자 UUID
SET @uuid4 = (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com');

INSERT INTO `woomsDB`.`wooms`
(`map_color_status`, `created_date`, `modified_date`, `wooms_id`, `invite_code`, `user_uuid`, `wooms_title`)
VALUES
    (0, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, UNHEX(REPLACE(UUID(), '-', '')), @uuid1, '싸피 이이공육 미식회'),
    (0, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, UNHEX(REPLACE(UUID(), '-', '')), @uuid2, '싸피초등학교 1학년 3반'),
    (0, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, UNHEX(REPLACE(UUID(), '-', '')), @uuid3, '한사랑 산악회'),
    (0, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 4, UNHEX(REPLACE(UUID(), '-', '')), @uuid4, '그룹이름뭘로짓지');

-- 7명 그룹
-- UUID 설정
SET @uuid_gr1 = (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com');
SET @uuid1 = (SELECT user_uuid FROM users WHERE user_email = 'example@example.com');
SET @uuid2 = (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com');
SET @uuid3 = (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com');
SET @uuid4 = (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com');
SET @uuid5 = (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com');
SET @uuid6 = (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com');

############################### 그룹원 데이터 삽입 ###############################

-- 그룹 ID 설정
SET @wooms_id_gr1 = 1; -- 싸피

INSERT INTO `woomsDB`.`wooms_enrollments`
(`status`, `created_date`, `modified_date`, `wooms_id`, `user_uuid`)
VALUES
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid_gr1),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid1),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid2),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid3),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid4),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid5),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr1, @uuid6);

-- 12명 그룹
-- UUID 설정
SET @uuid_gr2 = (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com');
SET @uuid7 = (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com');
SET @uuid8 = (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com');
SET @uuid9 = (SELECT user_uuid FROM users WHERE user_email = 'example9@example.com');
SET @uuid10 = (SELECT user_uuid FROM users WHERE user_email = 'example10@example.com');
SET @uuid11 = (SELECT user_uuid FROM users WHERE user_email = 'example11@example.com');
SET @uuid12 = (SELECT user_uuid FROM users WHERE user_email = 'example12@example.com');
SET @uuid13 = (SELECT user_uuid FROM users WHERE user_email = 'example13@example.com');
SET @uuid14 = (SELECT user_uuid FROM users WHERE user_email = 'example14@example.com');
SET @uuid15 = (SELECT user_uuid FROM users WHERE user_email = 'example15@example.com');
SET @uuid16 = (SELECT user_uuid FROM users WHERE user_email = 'example16@example.com');
SET @uuid17 = (SELECT user_uuid FROM users WHERE user_email = 'example17@example.com');

-- 그룹 ID 설정
SET @wooms_id_gr2 = 2; -- 싸피초등학교 1학년 3반

-- 그룹 생성자의 회원 등록
INSERT INTO `woomsDB`.`wooms_enrollments`
(`status`, `created_date`, `modified_date`, `wooms_id`, `user_uuid`)
VALUES
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid_gr2),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid7),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid8),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid9),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid10),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid11),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid12),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid13),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid14),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid15),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid16),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr2, @uuid17);

-- 10명 그룹
-- UUID 설정
SET @uuid_gr3 = (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com');
SET @uuid18 = (SELECT user_uuid FROM users WHERE user_email = 'example19@example.com');
SET @uuid19 = (SELECT user_uuid FROM users WHERE user_email = 'example20@example.com');
SET @uuid20 = (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com');
SET @uuid21 = (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com');
SET @uuid22 = (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com');
SET @uuid23 = (SELECT user_uuid FROM users WHERE user_email = 'example24@example.com');
SET @uuid24 = (SELECT user_uuid FROM users WHERE user_email = 'example25@example.com');
SET @uuid25 = (SELECT user_uuid FROM users WHERE user_email = 'example26@example.com');
SET @uuid26 = (SELECT user_uuid FROM users WHERE user_email = 'example27@example.com');

-- 그룹 ID 설정
SET @wooms_id_gr3 = 3; -- 한사랑 산악회

-- 그룹 생성자의 회원 등록
INSERT INTO `woomsDB`.`wooms_enrollments`
(`status`, `created_date`, `modified_date`, `wooms_id`, `user_uuid`)
VALUES
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid_gr3),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid18),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid19),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid20),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid21),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid22),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid23),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid24),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid25),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr3, @uuid26);

-- 3명 그룹
-- UUID 설정
SET @uuid_gr4 = (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com');
SET @uuid27 = (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com');
SET @uuid28 = (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com');

-- 그룹 ID 설정
SET @wooms_id_gr4 = 4; -- 그룹이름뭘로짓지

-- 그룹 생성자의 회원 등록
INSERT INTO `woomsDB`.`wooms_enrollments`
(`status`, `created_date`, `modified_date`, `wooms_id`, `user_uuid`)
VALUES
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr4, @uuid_gr4),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr4, @uuid27),
    (1, '2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', @wooms_id_gr4, @uuid28);


############################### 방명록 데이터 삽입 ###############################
-- 7명 그룹 방명록
-- 첫 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, 1, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '오늘도 코드 리뷰 열심히 하겠습니다!'),
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, 1, (SELECT user_uuid FROM users WHERE user_email = 'example@example.com'), '새로운 알고리즘 공부하기가 기대되네요.'),
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, 1, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), '오늘도 함께 열심히 해봐요!'),
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 4, 1, (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), '코딩이 즐겁습니다!'),
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 5, 1, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), '오늘도 문제 해결을 위해 최선을 다하겠습니다.'),
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 6, 1, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), '새로운 프로젝트 기대돼요!'),
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 7, 1, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), '코딩하면서 많은 것을 배우고 있습니다.');

-- 두 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 8, 1, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '어제 배운 내용 복습 중입니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 9, 1, (SELECT user_uuid FROM users WHERE user_email = 'example@example.com'), '오늘도 코드의 버그를 잡아보겠습니다!'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 10, 1, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), '함께하는 시간이 소중합니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 11, 1, (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), '오늘도 좋은 결과를 기대해봅니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 12, 1, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), '함께 배워서 더욱 발전해나가겠습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 13, 1, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), '새로운 기술을 배우는 즐거움이 큽니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 14, 1, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), '프로젝트 완성을 위해 최선을 다하겠습니다.');


-- 12명 그룹 방명록
-- 첫 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 15, 2, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '오랜만에 옛 추억이 떠오릅니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 16, 2, (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com'), '다들 잘 지내시나요?'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 17, 2, (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com'), '우리의 유쾌한 시절을 기억합니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 18, 2, (SELECT user_uuid FROM users WHERE user_email = 'example9@example.com'), '함께했던 그 시절이 그리워요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 19, 2, (SELECT user_uuid FROM users WHERE user_email = 'example10@example.com'), '추억의 이야기를 나눠보아요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 20, 2, (SELECT user_uuid FROM users WHERE user_email = 'example11@example.com'), '우리의 소중한 기억들을 공유해요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 21, 2, (SELECT user_uuid FROM users WHERE user_email = 'example12@example.com'), '추억을 회상하며 즐거운 시간 보내요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 22, 2, (SELECT user_uuid FROM users WHERE user_email = 'example13@example.com'), '이곳에서 또 다른 추억을 만들자요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 23, 2, (SELECT user_uuid FROM users WHERE user_email = 'example14@example.com'), '여기서도 함께 하는 것이 좋네요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 24, 2, (SELECT user_uuid FROM users WHERE user_email = 'example15@example.com'), '친구들과의 소중한 시간을 보내요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 25, 2, (SELECT user_uuid FROM users WHERE user_email = 'example16@example.com'), '다시 만날 날이 기대돼요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 26, 2, (SELECT user_uuid FROM users WHERE user_email = 'example17@example.com'), '소중한 친구들, 그리워요.');

-- 두 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 27, 2, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '오랜만에 함께 할 수 있어서 행복해요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 28, 2, (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com'), '옛날 이야기를 나누니 반갑습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 29, 2, (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com'), '여기서 다시 만날 수 있어 좋네요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 30, 2, (SELECT user_uuid FROM users WHERE user_email = 'example9@example.com'), '다들 잘 지내고 있나요?'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 31, 2, (SELECT user_uuid FROM users WHERE user_email = 'example10@example.com'), '추억을 되새기며 즐거운 시간 되세요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 32, 2, (SELECT user_uuid FROM users WHERE user_email = 'example11@example.com'), '즐거운 시간 보내기 위해 모였네요!'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 33, 2, (SELECT user_uuid FROM users WHERE user_email = 'example12@example.com'), '다시 만날 수 있어서 정말 반갑습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 34, 2, (SELECT user_uuid FROM users WHERE user_email = 'example13@example.com'), '우리의 소중한 시간, 잊지 않겠습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 35, 2, (SELECT user_uuid FROM users WHERE user_email = 'example14@example.com'), '좋은 추억을 만들어요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 36, 2, (SELECT user_uuid FROM users WHERE user_email = 'example15@example.com'), '옛날 추억을 다시 떠올리며 즐거운 시간 보내요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 37, 2, (SELECT user_uuid FROM users WHERE user_email = 'example16@example.com'), '친구들과의 시간이 늘 소중합니다.');

-- 10명 그룹 방명록
-- 첫 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 38, 3, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '오늘의 산행은 정말 즐거웠습니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 39, 3, (SELECT user_uuid FROM users WHERE user_email = 'example19@example.com'), '새로운 경로 탐방이 좋았어요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 40, 3, (SELECT user_uuid FROM users WHERE user_email = 'example20@example.com'), '좋은 날씨에 산책하기 좋았습니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 41, 3, (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com'), '다음 산행이 기대됩니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 42, 3, (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com'), '산에서 만난 사람들과의 대화가 좋았어요.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 43, 3, (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com'), '산속의 평화로움이 좋았습니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 44, 3, (SELECT user_uuid FROM users WHERE user_email = 'example24@example.com'), '다음 등산 코스도 기대됩니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 45, 3, (SELECT user_uuid FROM users WHERE user_email = 'example25@example.com'), '산에서의 만남이 즐거웠습니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 46, 3, (SELECT user_uuid FROM users WHERE user_email = 'example26@example.com'), '편안한 산행을 할 수 있었습니다.'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 47, 3, (SELECT user_uuid FROM users WHERE user_email = 'example27@example.com'), '산속에서 좋은 시간을 보냈습니다.');

-- 두 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 48, 3, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '산에서의 피로가 쌓였지만 즐거운 시간이었습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 49, 3, (SELECT user_uuid FROM users WHERE user_email = 'example19@example.com'), '이번 산행도 안전하게 마무리할 수 있어 좋았습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 50, 3, (SELECT user_uuid FROM users WHERE user_email = 'example20@example.com'), '다음 주에는 더 멋진 산행을 기대합니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 51, 3, (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com'), '즐거운 시간 보내서 좋았습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 52, 3, (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com'), '산속의 공기가 참 좋네요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 53, 3, (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com'), '멋진 경치를 즐길 수 있어서 좋았습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 54, 3, (SELECT user_uuid FROM users WHERE user_email = 'example24@example.com'), '산에서의 시간은 언제나 소중합니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 55, 3, (SELECT user_uuid FROM users WHERE user_email = 'example25@example.com'), '오늘의 산행은 기억에 남습니다.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 56, 3, (SELECT user_uuid FROM users WHERE user_email = 'example26@example.com'), '힘든 산행 후의 성취감이 크네요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 57, 3, (SELECT user_uuid FROM users WHERE user_email = 'example27@example.com'), '산속에서의 행복한 시간이었습니다.');

-- 3명 그룹 방명록
-- 첫 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 58, 4, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), '오늘은 기분이 좋네요!'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 59, 4, (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), '다들 잘 지내고 있나요?'),
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 60, 4, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), '즐거운 하루 보내세요!');

-- 두 번째 방명록
INSERT INTO `woomsDB`.`wooms_comments`
(`created_date`, `modified_date`, `wooms_comment_id`, `wooms_id`, `user_uuid`, `wooms_comment_content`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 61, 4, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), '오늘도 좋은 하루 되길!'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 62, 4, (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), '즐거운 시간이 되었으면 좋겠어요.'),
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 63, 4, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), '다음에 또 만나요!');

############################### 그룹이름뭘로짓지 (wooms_id = 4) 그룹 사연 데이터 삽입 ###############################
-- 첫 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 4, 1, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), '우리 모임의 첫 만남에서 찍은 사진이 아직도 기억에 남아요. 그때의 기분이 언제나 그리워요.', 'example4_1');

-- 두 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 4, 2, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), '우리의 마지막 여행은 정말 잊지 못할 추억이었습니다. 다음에도 이런 재미있는 일정을 또 만들어 봅시다!', 'example4_2');

############################### 싸피 (wooms_id = 1) 그룹 사연 데이터 삽입 ###############################
-- 첫 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, 3, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '입학 초기의 낯선 환경에서 모두가 서로를 돕고 격려했던 기억이 떠오릅니다. 이제는 모두가 정말 좋은 친구가 되었네요.', 'example1_3');

-- 두 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 1, 4, (SELECT user_uuid FROM users WHERE user_email = 'example@example.com'), '우리의 프로젝트는 시작할 때는 막막했지만, 함께 힘을 합쳐 성공적으로 끝낼 수 있어서 정말 뿌듯합니다. 이 경험을 평생 간직할게요.', 'example1_4');

-- 세 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, 5, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), '함께 공부하고, 프로젝트를 진행하면서 쌓인 소중한 시간들이 정말 많은 힘이 되었어요. 다음에도 또 만나서 함께해요!', 'example1_5');

-- 네 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 1, 6, (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), '서로의 성장과 변화를 함께 지켜볼 수 있어 행복했습니다. 이 여정이 계속되기를 바랍니다.', 'example1_6');

-- 다섯 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, 7, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), '모든 수업이 끝나고 동료들과의 마지막 시간이 너무 소중했습니다. 이 경험이 평생의 자산이 될 것 같습니다.', 'example1_7');

-- 여섯 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 1, 8, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), '함께 프로젝트를 하면서 느꼈던 팀워크가 정말 멋졌어요. 이 경험을 바탕으로 더 성장하겠습니다.', 'example1_8');

-- 일곱 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, 9, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), '우리의 프로젝트는 도전적이었지만, 함께 했기에 성공할 수 있었습니다. 앞으로도 서로 응원하며 지내길 바랍니다.', 'example1_9');

-- 여덟 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 1, 10, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '이곳에서 많은 것을 배우고, 새로운 친구들을 만나서 행복했습니다. 이번 경험이 앞으로의 길에 큰 도움이 될 것입니다.', 'example1_10');

-- 아홉 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 1, 11, (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com'), '처음 만난 순간부터 끝까지 많은 것을 배웠습니다. 다음에도 이런 멋진 기회가 오기를 바랍니다.', 'example1_11');

-- 열 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 1, 12, (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com'), '함께한 시간들이 너무나 소중했고, 항상 기억에 남을 것 같습니다. 여러분 모두 감사합니다!', 'example1_12');

############################### 싸피초등학교 1학년 3반 (wooms_id = 2) 그룹 사연 데이터 삽입 ###############################
-- 첫 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, 13, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '오랜만에 만난 친구들과의 재회가 너무 즐거웠습니다. 옛 추억을 떠올리며 많은 이야기를 나누어서 좋았어요.', 'example2_13');

-- 두 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 2, 14, (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com'), '우리의 오랜 우정을 다시 확인할 수 있어서 정말 행복했습니다. 다시 모여서 이런 시간을 가지게 되어 감사해요.', 'example2_14');

-- 세 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, 15, (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com'), '다시 만난 친구들과의 시간은 늘 소중합니다. 어린 시절의 추억을 함께 나누어 너무 좋았어요.', 'example2_15');

-- 네 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 2, 16, (SELECT user_uuid FROM users WHERE user_email = 'example9@example.com'), '친구들과의 모임 덕분에 즐거운 추억을 만들 수 있었습니다. 앞으로도 자주 만났으면 좋겠어요.', 'example2_16');

-- 다섯 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, 17, (SELECT user_uuid FROM users WHERE user_email = 'example10@example.com'), '그때 그 시절을 다시 떠올리면서 웃음꽃을 피웠던 시간들이 기억에 남습니다. 정말 즐거운 시간이었습니다.', 'example2_17');

-- 여섯 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 2, 18, (SELECT user_uuid FROM users WHERE user_email = 'example11@example.com'), '다시 만난 친구들과의 이야기는 늘 재미있고 의미 있었습니다. 앞으로도 자주 모여서 이런 시간을 가지면 좋겠어요.', 'example2_18');

-- 일곱 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, 19, (SELECT user_uuid FROM users WHERE user_email = 'example12@example.com'), '어릴 적 친구들과의 모임이 정말 그리웠습니다. 다시 만날 날을 기다리겠습니다.', 'example2_19');

-- 여덟 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 2, 20, (SELECT user_uuid FROM users WHERE user_email = 'example13@example.com'), '우리가 다시 모여서 옛 추억을 이야기할 수 있어서 너무 좋았습니다. 앞으로도 자주 만나요!', 'example2_20');

-- 아홉 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 2, 21, (SELECT user_uuid FROM users WHERE user_email = 'example14@example.com'), '친구들과 함께 보낸 시간들이 정말 소중했습니다. 이 시간을 계속 간직할 수 있기를 바랍니다.', 'example2_21');

-- 열 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 2, 22, (SELECT user_uuid FROM users WHERE user_email = 'example15@example.com'), '함께했던 추억들이 새록새록 떠오르네요. 다시 만날 날을 기다리며 잘 지내요!', 'example2_22');

############################### 한사랑 산악회 (wooms_id = 3) 그룹 사연 데이터 삽입 ###############################
-- 첫 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, 23, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '오늘의 산행도 무사히 마무리되어 정말 좋았습니다. 멋진 경치와 함께 즐거운 시간을 보냈습니다.', 'example3_23');

-- 두 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 3, 24, (SELECT user_uuid FROM users WHERE user_email = 'example19@example.com'), '힘들었던 산행이었지만, 함께 가는 친구들이 있어 즐거운 시간이었습니다. 다음에도 함께 산을 오르길 바랍니다.', 'example3_24');

-- 세 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, 25, (SELECT user_uuid FROM users WHERE user_email = 'example20@example.com'), '등산하면서 만난 친구들 덕분에 즐거운 시간이었습니다. 건강을 챙기며 산행을 계속하고 싶습니다.', 'example3_25');

-- 네 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 3, 26, (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com'), '오늘의 산행도 서로 돕고 함께한 시간이었습니다. 언제나 건강하고 행복한 시간들만 있기를 바랍니다.', 'example3_26');

-- 다섯 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, 27, (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com'), '산을 오르는 동안의 대화가 너무 즐거웠습니다. 이 기분을 계속 간직하며 다음 산행을 기대합니다.', 'example3_27');

-- 여섯 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 3, 28, (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com'), '산행 중에 본 아름다운 풍경 덕분에 더욱 기운이 났습니다. 함께한 모든 분들에게 감사드립니다.', 'example3_28');

-- 일곱 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, 29, (SELECT user_uuid FROM users WHERE user_email = 'example24@example.com'), '오늘의 산행에서 많은 이야기를 나누며 즐거운 시간을 보냈습니다. 다음에도 함께하기를 기대합니다.', 'example3_29');

-- 여덟 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 3, 30, (SELECT user_uuid FROM users WHERE user_email = 'example25@example.com'), '산행 중에 나눈 대화와 웃음이 아직도 기억에 남습니다. 건강을 챙기며 산행을 계속해요.', 'example3_30');

-- 아홉 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-08 03:36:40.909393', '2024-08-08 03:36:40.909393', 3, 31, (SELECT user_uuid FROM users WHERE user_email = 'example26@example.com'), '모든 등산이 끝난 후 함께 나눈 이야기들이 정말 소중했습니다. 건강하게 오래오래 같이 산을 오르고 싶습니다.', 'example3_31');

-- 열 번째 사연
INSERT INTO `woomsDB`.`wooms_stories`
(`created_date`, `modified_date`, `wooms_id`, `wooms_story_id`, `user_uuid`, `wooms_story_content`, `wooms_story_file_name`)
VALUES
    ('2024-08-09 03:36:40.909393', '2024-08-09 03:36:40.909393', 3, 32, (SELECT user_uuid FROM users WHERE user_email = 'example27@example.com'), '오늘의 산행에서 자연의 아름다움과 함께 좋은 사람들과 시간을 보냈습니다. 다음 산행도 기대합니다.', 'example3_32');

############################### 싸피 편지 데이터 삽입 ###############################

##################### 그룹원 -> 그룹장 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 1, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), '안녕하세요! 싸피에서의 시간은 정말 뜻깊었어요. 교육을 통해 많은 것을 배우고, 멘토링을 통해 성장할 수 있었습니다. 감사합니다. 앞으로도 계속해서 많은 도움과 지도 부탁드립니다. 항상 응원하고 있습니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 2, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), '안녕하세요! 싸피에서의 멘토링 덕분에 많은 도움을 받았습니다. 유익한 교육과 피드백에 항상 감사드리며, 앞으로도 많은 지도 부탁드립니다. 항상 건강하시고 행복하세요!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 3, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), '안녕하세요! 싸피에서의 교육은 정말 유익했습니다. 멘토님의 열정과 노력이 큰 힘이 되었습니다. 앞으로도 계속해서 많은 조언과 지원 부탁드리며, 함께 성장해 나가기를 기대합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 4, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), '안녕하세요! 싸피에서 멘토님과 함께하는 시간이 정말 뜻깊었습니다. 모든 과정에서의 세심한 배려와 조언 덕분에 많은 것을 배웠습니다. 앞으로도 계속해서 많은 도움 부탁드립니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 5, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), '안녕하세요! 싸피에서 멘토님 덕분에 많은 것을 배울 수 있었습니다. 멘토님의 조언이 많은 도움이 되었습니다. 앞으로도 계속해서 많은 가르침 부탁드립니다. 감사합니다!');

##################### 그룹장 -> 그룹원 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 6, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '안녕하세요, 싸피의 소중한 동료님! 여러분과 함께 공부할 수 있어 정말 기쁘고, 각자의 열정이 더 큰 힘이 됩니다. 함께 성장하고 나아가면서 더 많은 성공을 이룰 수 있기를 바랍니다. 여러분 모두의 노력이 항상 빛을 발하길 응원합니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 7, (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '안녕하세요, 싸피의 소중한 동료님! 여러분과 함께 공부할 수 있어 정말 기쁘고, 각자의 열정이 더 큰 힘이 됩니다. 함께 성장하고 나아가면서 더 많은 성공을 이룰 수 있기를 바랍니다. 여러분 모두의 노력이 항상 빛을 발하길 응원합니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 8, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '안녕하세요, 싸피의 소중한 동료님! 여러분과 함께 공부할 수 있어 정말 기쁘고, 각자의 열정이 더 큰 힘이 됩니다. 함께 성장하고 나아가면서 더 많은 성공을 이룰 수 있기를 바랍니다. 여러분 모두의 노력이 항상 빛을 발하길 응원합니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 9, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '안녕하세요, 싸피의 소중한 동료님! 여러분과 함께 공부할 수 있어 정말 기쁘고, 각자의 열정이 더 큰 힘이 됩니다. 함께 성장하고 나아가면서 더 많은 성공을 이룰 수 있기를 바랍니다. 여러분 모두의 노력이 항상 빛을 발하길 응원합니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 10, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), '안녕하세요, 싸피의 소중한 동료님! 여러분과 함께 공부할 수 있어 정말 기쁘고, 각자의 열정이 더 큰 힘이 됩니다. 함께 성장하고 나아가면서 더 많은 성공을 이룰 수 있기를 바랍니다. 여러분 모두의 노력이 항상 빛을 발하길 응원합니다. 감사합니다!');

############################### 싸피초등학교 편지 데이터 삽입 ###############################

##################### 그룹원 -> 그룹장 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 14, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com'), '안녕하세요! 초등학교 시절의 친구들과 다시 만나서 너무 즐거웠습니다. 그때의 추억을 되새기며, 함께하는 시간들이 더욱 특별하게 느껴졌어요. 앞으로도 이런 만남을 자주 가질 수 있기를 바랍니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 15, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com'), '안녕하세요! 지난번 모임에서 함께한 시간이 정말 즐거웠습니다. 초등학교 시절의 친구들과 다시 만나는 것은 큰 기쁨이었습니다. 앞으로도 자주 만나서 소중한 시간을 함께 보낼 수 있기를 바랍니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 16, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example9@example.com'), '안녕하세요! 모임에서의 순간들이 정말 소중하게 느껴졌습니다. 초등학교 시절의 친구들과 다시 만나서 너무 좋았어요. 다음에도 이런 기회가 있기를 기대하며, 항상 건강하시길 바랍니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 17, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example10@example.com'), '안녕하세요! 초등학교 시절의 추억이 담긴 모임에서 함께할 수 있어서 좋았습니다. 다음에도 이렇게 좋은 시간들을 함께 보내길 바랍니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 18, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example11@example.com'), '안녕하세요! 초등학교 시절의 친구들과 다시 만나서 많은 추억을 만들 수 있었습니다. 앞으로도 자주 만날 수 있기를 기대하며, 다음 만남을 기다립니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 19, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example12@example.com'), '안녕하세요! 함께한 모임이 정말 즐거웠습니다. 초등학교 시절의 친구들과 다시 만나서 많은 즐거운 추억을 만들 수 있었습니다. 다음에도 자주 만날 수 있기를 바랍니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 20, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example13@example.com'), '안녕하세요! 모임에서의 좋은 시간들 덕분에 즐거운 추억이 생겼습니다. 초등학교 시절의 친구들과 함께하는 시간이 정말 좋았어요. 앞으로도 자주 만나서 좋은 시간을 보내길 기대합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 21, (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example14@example.com'), '안녕하세요! 초등학교 시절의 친구들과 다시 만나는 기회가 되어 너무 기쁩니다. 함께한 시간들이 즐거웠습니다. 다음에도 자주 만날 수 있기를 바랍니다. 항상 건강하세요!');

##################### 그룹장 -> 그룹원 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 22, (SELECT user_uuid FROM users WHERE user_email = 'example7@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '안녕하세요! 친구들과 함께했던 소중한 시간들을 다시 만날 수 있어서 너무 기쁩니다. 여러분 덕분에 초등학교 시절의 행복한 추억을 다시 떠올릴 수 있었어요. 앞으로도 자주 모여서 즐거운 시간 보내요!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 23, (SELECT user_uuid FROM users WHERE user_email = 'example8@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '안녕하세요! 친구들과 함께했던 소중한 시간들을 다시 만날 수 있어서 너무 기쁩니다. 여러분 덕분에 초등학교 시절의 행복한 추억을 다시 떠올릴 수 있었어요. 앞으로도 자주 모여서 즐거운 시간 보내요!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 24, (SELECT user_uuid FROM users WHERE user_email = 'example9@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '안녕하세요! 친구들과 함께했던 소중한 시간들을 다시 만날 수 있어서 너무 기쁩니다. 여러분 덕분에 초등학교 시절의 행복한 추억을 다시 떠올릴 수 있었어요. 앞으로도 자주 모여서 즐거운 시간 보내요!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 25, (SELECT user_uuid FROM users WHERE user_email = 'example10@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '안녕하세요! 친구들과 함께했던 소중한 시간들을 다시 만날 수 있어서 너무 기쁩니다. 여러분 덕분에 초등학교 시절의 행복한 추억을 다시 떠올릴 수 있었어요. 앞으로도 자주 모여서 즐거운 시간 보내요!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 26, (SELECT user_uuid FROM users WHERE user_email = 'example11@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example6@example.com'), '안녕하세요! 친구들과 함께했던 소중한 시간들을 다시 만날 수 있어서 너무 기쁩니다. 여러분 덕분에 초등학교 시절의 행복한 추억을 다시 떠올릴 수 있었어요. 앞으로도 자주 모여서 즐거운 시간 보내요!');


############################### 한사랑 산악회 편지 데이터 삽입 ###############################

##################### 그룹원 -> 그룹장 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 27, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example19@example.com'), '안녕하세요! 산행에서의 시간들이 너무 좋았습니다. 함께 나눈 대화와 경치가 정말 인상적이었어요. 다음 산행에서도 함께 하길 기대하며, 건강한 하루 보내시길 바랍니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 28, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example20@example.com'), '안녕하세요! 함께한 산행에서의 시간이 너무 좋았습니다. 좋은 추억이 생겨서 기쁩니다. 앞으로도 건강하게 함께 산을 오르며 좋은 시간을 보내길 바랍니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 29, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com'), '안녕하세요! 산행에서의 소중한 시간들을 함께할 수 있어서 좋았습니다. 대화와 경험들이 큰 의미가 있었습니다. 다음에도 좋은 산행을 함께하길 기대합니다. 감사합니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 30, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com'), '안녕하세요! 함께한 산행에서 좋은 시간들을 보낼 수 있어서 좋았습니다. 다음에도 이런 기회가 있기를 기대하며, 건강한 하루 보내세요!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 31, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com'), '안녕하세요! 산행에서의 순간들이 정말 소중했습니다. 함께 나눈 시간들이 좋은 추억으로 남았습니다. 앞으로도 건강하고 즐거운 산행이 되길 바랍니다!');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 32, (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example24@example.com'), '안녕하세요! 산행에서의 좋은 시간들이 큰 의미가 있었습니다. 앞으로도 함께 건강한 산행을 계속할 수 있기를 기대합니다. 감사합니다!');

##################### 그룹장 -> 그룹원 편지 데이터 삽입 #####################

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 33, (SELECT user_uuid FROM users WHERE user_email = 'example19@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '안녕하세요! 산행 중 여러분과 함께할 수 있어서 매우 행복했습니다. 좋은 대화와 즐거운 순간들이 오래도록 기억에 남을 것 같습니다. 앞으로도 건강하게 계속해서 산행을 즐기며 좋은 추억을 만들어가길 바랍니다.');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 34, (SELECT user_uuid FROM users WHERE user_email = 'example20@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '안녕하세요! 산행 중 여러분과 함께할 수 있어서 매우 행복했습니다. 좋은 대화와 즐거운 순간들이 오래도록 기억에 남을 것 같습니다. 앞으로도 건강하게 계속해서 산행을 즐기며 좋은 추억을 만들어가길 바랍니다.');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 35, (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '안녕하세요! 산행 중 여러분과 함께할 수 있어서 매우 행복했습니다. 좋은 대화와 즐거운 순간들이 오래도록 기억에 남을 것 같습니다. 앞으로도 건강하게 계속해서 산행을 즐기며 좋은 추억을 만들어가길 바랍니다.');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 36, (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '안녕하세요! 산행 중 여러분과 함께할 수 있어서 매우 행복했습니다. 좋은 대화와 즐거운 순간들이 오래도록 기억에 남을 것 같습니다. 앞으로도 건강하게 계속해서 산행을 즐기며 좋은 추억을 만들어가길 바랍니다.');

INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 37, (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example18@example.com'), '안녕하세요! 산행 중 여러분과 함께할 수 있어서 매우 행복했습니다. 좋은 대화와 즐거운 순간들이 오래도록 기억에 남을 것 같습니다. 앞으로도 건강하게 계속해서 산행을 즐기며 좋은 추억을 만들어가길 바랍니다.');


############################### 그룹이름뭘로짓지 편지 데이터 삽입 ###############################

##################### 그룹원 -> 그룹장 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 38, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), '안녕하세요! 함께 보내는 시간들이 늘 즐겁고 소중합니다. 우리 그룹이 함께 만들어가는 추억들이 너무 좋네요. 앞으로도 이런 좋은 시간들을 계속 함께하길 바랍니다. 감사합니다!');

##################### 그룹장 -> 그룹원 편지 데이터 삽입 #####################
INSERT INTO `woomsDB`.`user_letters` (`user_letter_status`, `created_date`, `modified_date`, `receive_date`, `sent_date`, `user_letter_id`, `user_receiver_uuid`, `user_sender_uuid`, `user_letter_content`)
VALUES (0, '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', '2024-08-10 03:36:40.909393', 39, (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), '안녕하세요! 우리의 즐거운 모임이 계속되기를 바라며, 좋은 추억들을 만들어가는 게 너무 기쁩니다. 함께한 모든 순간들이 소중합니다. 앞으로도 자주 모여서 즐거운 시간 보내길 바랍니다!');


############################### 싸피 그룹 사진 데이터 삽입 ###############################

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (1, 0, '2024-01-05 10:00:00', '2024-01-05 10:00:00', 1, 1, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_nickname FROM users WHERE user_email = 'ssafy@ssafy.com'), NULL, '즐거운 입학식!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (2, 0, '2024-01-15 11:30:00', '2024-01-15 11:30:00', 1, 2, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_nickname FROM users WHERE user_email = 'ssafy@ssafy.com'), NULL, '팀원들과 저녁식사');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (3, 0, '2024-01-25 14:20:00', '2024-01-25 14:20:00', 1, 3, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_nickname FROM users WHERE user_email = 'ssafy@ssafy.com'), NULL, '팀원들과 노래방!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (4, 0, '2024-02-12 13:45:00', '2024-02-12 13:45:00', 1, 4, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example5@example.com'), NULL, '성우가 해준 고기');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (5, 0, '2024-02-20 16:00:00', '2024-02-20 16:00:00', 1, 5, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example4@example.com'), NULL, '맛있었던 명지 꼬지집');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (6, 0, '2024-03-05 11:00:00', '2024-03-05 11:00:00', 1, 6, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example1@example.com'), NULL, '도언이가 해준 저녁1');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (7, 0, '2024-03-15 12:30:00', '2024-03-15 12:30:00', 1, 7, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example1@example.com'), NULL, '도언이가 해준 저녁2');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (8, 0, '2024-03-25 10:00:00', '2024-03-25 10:00:00', 1, 9, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example5@example.com'), NULL, '성우가 해준 저녁-1');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (9, 0, '2024-04-05 14:00:00', '2024-04-05 14:00:00', 1, 10, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example5@example.com'), NULL, '성우가 해준 저녁-2');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (10, 0, '2024-04-15 15:30:00', '2024-04-15 15:30:00', 1, 11, (SELECT user_uuid FROM users WHERE user_email = 'example5@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example5@example.com'), NULL, '성우가 해준 저녁-3');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (11, 0, '2024-04-25 12:30:00', '2024-04-25 12:30:00', 1, 12, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_nickname FROM users WHERE user_email = 'ssafy@ssafy.com'), NULL, '다같이 카페공부');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (12, 0, '2024-05-05 10:00:00', '2024-05-05 10:00:00', 1, 13, (SELECT user_uuid FROM users WHERE user_email = 'example@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example@example.com'), NULL, '도예가 해준 피자');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (13, 0, '2024-05-15 11:30:00', '2024-05-15 11:30:00', 1, 14, (SELECT user_uuid FROM users WHERE user_email = 'example@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example@example.com'), NULL, '도예가 해준 자몽빙수');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (14, 0, '2024-05-25 13:00:00', '2024-05-25 13:00:00', 1, 15, (SELECT user_uuid FROM users WHERE user_email = 'example1@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example1@example.com'), NULL, '도언이집 고양이');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (15, 0, '2024-06-05 12:00:00', '2024-06-05 12:00:00', 1, 16, (SELECT user_uuid FROM users WHERE user_email = 'ssafy@ssafy.com'), (SELECT user_nickname FROM users WHERE user_email = 'ssafy@ssafy.com'), NULL, '팀원들과 바닷가');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (16, 0, '2024-06-15 14:30:00', '2024-06-15 14:30:00', 1, 17, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example4@example.com'), NULL, '팀원들과 맛집탐방 - 1');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (17, 0, '2024-06-25 16:00:00', '2024-06-25 16:00:00', 1, 18, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example4@example.com'), NULL, '팀원들과 맛집탐방 - 2');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (18, 0, '2024-07-05 11:00:00', '2024-07-05 11:00:00', 1, 19, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example4@example.com'), NULL, '팀원들과 맛집탐방 - 3');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (19, 0, '2024-07-15 12:30:00', '2024-07-15 12:30:00', 1, 20, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example4@example.com'), NULL, '팀원들과 맛집탐방 - 4');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (20, 0, '2024-07-25 10:00:00', '2024-07-25 10:00:00', 1, 21, (SELECT user_uuid FROM users WHERE user_email = 'example4@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example4@example.com'), NULL, '팀원들과 맛집탐방 - 5');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (21, 0, '2024-08-05 14:00:00', '2024-08-05 14:00:00', 1, 22, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example3@example.com'), NULL, '팀원들과 양갈비');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (22, 0, '2024-08-15 11:30:00', '2024-08-15 11:30:00', 1, 23, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example3@example.com'), NULL, '팀원들과 문화생활 - 1');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (23, 0, '2024-08-25 13:00:00', '2024-08-25 13:00:00', 1, 24, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example3@example.com'), NULL, '팀원들과 문화생활 - 2');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (24, 0, '2024-05-05 10:00:00', '2024-05-05 10:00:00', 2, 25, (SELECT user_uuid FROM users WHERE user_email = 'example3@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example3@example.com'), NULL, '팀원들과 문화생활 - 3');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (25, 0, '2024-05-15 11:30:00', '2024-05-15 11:30:00', 2, 26, (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example2@example.com'), NULL, '대영이형 집들이 - 1');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (26, 0, '2024-05-25 12:00:00', '2024-05-25 12:00:00', 2, 27, (SELECT user_uuid FROM users WHERE user_email = 'example2@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example2@example.com'), NULL, '대영이형 집들이 - 2');

############################### 싸피초등학교 그룹 사진 데이터 삽입 ###############################

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (28, 0, '2024-06-05 13:00:00', '2024-06-05 13:00:00', 2, 28, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example28@example.com'), NULL, '초등학교 시절 친구들과 함께한 여름 소풍. 모두 즐거워하는 모습!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (29, 0, '2024-06-15 14:00:00', '2024-06-15 14:00:00', 2, 29, (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example29@example.com'), NULL, '여름날 초등학교 동창들이 함께한 바비큐 파티. 즐거운 순간이 가득!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (30, 0, '2024-06-25 15:00:00', '2024-06-25 15:00:00', 2, 30, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example30@example.com'), NULL, '여름 끝자락에 진행한 동창회. 추억을 되새기며 즐거운 시간을 보낸 순간!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (31, 0, '2024-07-05 16:00:00', '2024-07-05 16:00:00', 2, 31, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example28@example.com'), NULL, '초등학교 동창 모임에서의 재미있는 게임 시간. 웃음과 재미가 가득!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (32, 0, '2024-07-15 11:00:00', '2024-07-15 11:00:00', 2, 32, (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example29@example.com'), NULL, '동창 모임에서 함께한 여름 해변에서의 즐거운 시간!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (33, 0, '2024-07-25 12:30:00', '2024-07-25 12:30:00', 2, 33, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example30@example.com'), NULL, '여름날의 동창 모임. 모두가 웃고 즐기며 함께한 시간!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (34, 0, '2024-08-05 14:00:00', '2024-08-05 14:00:00', 2, 34, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example28@example.com'), NULL, '여름의 마지막 동창 모임. 모두 함께하는 즐거운 순간!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (35, 0, '2024-08-15 15:00:00', '2024-08-15 15:00:00', 2, 35, (SELECT user_uuid FROM users WHERE user_email = 'example29@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example29@example.com'), NULL, '동창 모임에서의 여름 마무리 파티. 다 함께 행복한 시간을 보내는 모습!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (36, 0, '2024-08-25 16:00:00', '2024-08-25 16:00:00', 2, 36, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example30@example.com'), NULL, '여름의 마지막 동창 모임에서의 추억. 모두 함께 웃으며 즐거운 시간을 보내는 모습!');

############################### 한사랑 산악회 그룹 사진 데이터 삽입 ###############################

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (37, 0, '2024-08-05 07:00:00', '2024-08-05 07:00:00', 3, 37, (SELECT user_uuid FROM users WHERE user_email = 'example21@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example21@example.com'), NULL, '여름 산악회에서의 일출 장면. 아름다운 자연의 시작을 맞이하는 순간!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (38, 0, '2024-08-15 09:00:00', '2024-08-15 09:00:00', 3, 38, (SELECT user_uuid FROM users WHERE user_email = 'example22@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example22@example.com'), NULL, '산악회에서의 등산 중 멋진 풍경을 배경으로 한 사진. 자연과 함께하는 순간!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (39, 0, '2024-08-25 10:00:00', '2024-08-25 10:00:00', 3, 39, (SELECT user_uuid FROM users WHERE user_email = 'example23@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example23@example.com'), NULL, '산악회 마지막 일정. 멋진 경치를 감상하며 팀원들과 함께한 순간!');

############################### 그룹이름뭘로짓지 그룹 사진 데이터 삽입 ###############################
INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (40, 0, '2024-08-05 12:00:00', '2024-08-05 12:00:00', 4, 40, (SELECT user_uuid FROM users WHERE user_email = 'example27@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example27@example.com'), NULL, '여름의 끝자락, 친구들과 함께한 마지막 바베큐 파티. 즐거운 마무리!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (41, 0, '2024-08-15 15:00:00', '2024-08-15 15:00:00', 4, 41, (SELECT user_uuid FROM users WHERE user_email = 'example28@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example28@example.com'), NULL, '여름 마지막 날, 친구들과 함께한 해변에서의 멋진 일몰. 추억이 가득!');

INSERT INTO `woomsDB`.`wooms_photos` (`wooms_map_id`, `wooms_photo_flipped`, `created_date`, `modified_date`, `wooms_id`, `wooms_photo_id`, `user_uuid`, `wooms_nickname`, `wooms_photo_file_path`, `wooms_photo_summary`)
VALUES (42, 0, '2024-08-25 17:00:00', '2024-08-25 17:00:00', 4, 42, (SELECT user_uuid FROM users WHERE user_email = 'example30@example.com'), (SELECT user_nickname FROM users WHERE user_email = 'example30@example.com'), NULL, '여름 끝자락, 친구들과 함께한 시원한 수영장에서의 멋진 순간!');

set foreign_key_checks = 1;
