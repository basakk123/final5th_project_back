-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 1, 'iamsteel', 'steelsoo@gmail.com', '010-2111-4376', 1234, '김철수', NULL, '천리길도 한걸음부터', now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 2, 'lee0',  'lee0hee@gmail.com', '010-7303-8372', 1234, '이영희', NULL, '작심0일', now(), now(),NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 3, 'lala', 'lala1111@gmail.com', Null, 1234, NULL, NULL, NULL, now(), now(),NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 4, 'goddess', 'm5y6@gmail.com', '010-5005-3274', 1234, NULL, NULL, NULL, now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 5, 'tsktsk', 'i5wu@gmail.com', '010-3179-9765', 1234, '박정우', NULL, '안녕하세요', now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 6, 'amongst', 'easgwhf@gmail.com', '010-3179-9766', 1234, NULL, NULL, '인간의 욕심은 끝이 없고 같은 실수를 반복한다', now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 7, 'eek', 'uwj8iuw@gmail.com', NULL , 1234, '최수빈', NULL, NULL, now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 8, 'kiddingly', '4z80x2d@gmail.com','010-8975-9900' , 1234, '정이경', NULL, 'D-48', now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 9, 'videotape', 'ge3s212@gmail.com', NULL , 1234, NULL, NULL, NULL, now(), now(), NULL, NULL, NULL, NULL, NULL);

-- insert into users (user_id, user_name,user_email,user_phonenumber,user_password,user_realname,user_imgfile,user_profile_intro,user_created_at,user_updated_at , user_web_link, user_image_url,user_image_type, user_image_name, user_image_uuid) 
-- VALUES( 10, 'attentive', '68wb1zd@gmail.com', Null, 1234, NULL, NULL, NULL, now(), now(), NULL, NULL, NULL, NULL, NULL);



-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 1, 1, 2, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 2, 1, 3, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 3, 1, 4, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 4, 2, 1, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 5, 2, 3, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 6, 3, 1, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 7, 3, 2, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 8, 4, 1, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 9, 4, 7, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 10, 4, 2, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 11, 5, 2, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 12, 5, 1, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 13, 6, 7, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 14, 7, 6, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 15, 7, 4, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 16, 1, 5, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 17, 1, 7, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 18, 8, 1, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 19, 1, 8, now() );
-- insert into follow ( follow_id, user_id,following_user_id, created_at) VALUES( 20, 10, 1, now() );



-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 1, 2, 5);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 2, 6, 5);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 3, 8, 5);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 4, 2, 5);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 5, 2, 1);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 6, 1, 1);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 7, 1, 2);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 8, 1, 3);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 9, 1, 4);
-- insert into post_like (post_like_id, user_id, schedule_id ) VALUES( 10, 3, 2);



-- insert into news (news_id, target_user_id, user_id, schedule_id, comments_id, follow_id )
-- VALUES( 1, 1, 2, 1,1,1);



-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 1, 1, '영어 1강 보기', 1 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 2, 1, '영어 2강 보기', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 3, 2, '헬스장 가기', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 4, 2, '물마시기1', 1 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 5, 2, '물마시기2', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 6, 2, '물마시기3', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 7, 2, '물마시기4', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 8, 4, 'ㅇㅇ', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 9, 7, 'test', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 10, 7, 'adsfadsf', 1 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 11, 6, '금주1일차', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 12, 5, '공부하기', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 13, 5, '운동하기', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 14, 9, 'ddddd', 0 );
-- insert into todo ( todo_id, user_id, todo_title, is_finished) VALUES( 15, 10, 'ㅋ', 0 );




-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 1, '면접보기', 20230101130000, 20230102130000, 20230102140000, '서면', '내용 1', null, null, 1, 1);
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 2, '운동', 20230101130000, 20230102150000, 20230102170000, '헬스장', '내용 2', '노트2', null, 2, 5 );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 3, '산책하기', 20230101130000, 20230103130000, 20230103140000, '광안리', '내용 3', null, null, 3, 2 );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 4, '공부하기', 20230101010000, 20230104090000, 20230104120000, null, '내용 4', '노트4', null, 3, 2 );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 5, '카공!!', 20230101010000, 20230105140000, null, '카페', '내용 5', null, null, 1, 1);
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 6, 'test', now(), 20230101130000, 20230110130000, 'test', 'test', 'test', null, 4, 7 );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 7, 'test2', now(), 20230102130000, 20230115130000, 'test2', 'test2', 'test2', null, 5, 7 );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 8, '올리브영', now(), 20230103130000, 20230215130000, '서면', '립밤, 핸드크림', null, null, 6, 2 );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 9, '1', now(), 20230204130000, 20230210130000, null, null, null, null, 7, 3  );
-- insert into schedule ( schedule_id, schedule_title, schedule_created_at, schedule_start_at, schedule_finish_at, schedule_location, schedule_content, schedule_note, field, category_id, user_id )	
-- VALUES( 10, '데이트', now(), 20230305130000, 20230310130000, '카페', null, null, null, 1, 1 );



-- insert into sub_schedule ( sub_schedule_id, schedule_id, sub_schedule_title,sub_schedule_created_at, sub_schedule_start_at, sub_schedule_finish_at, sub_schedule_location, sub_schedule_content)
-- VALUES( 1, 3, '다이소 들리기', now(), null, null, '다이소 서면점', '여행용 소분 용기' );



-- insert into schedule_image (schedule_image_id, schedule_id, shcedule_image_url, schedule_image_type, schedule_image_name, schedule_image_uuid, shcedule_image_created_at )
-- VALUES( 1, 1, 'https://url.kr/e821j6', 'webp', 'RESUME_Templete_1_KR', 'uuid', 20230102130000 );
-- insert into schedule_image (schedule_image_id, schedule_id, shcedule_image_url, schedule_image_type, schedule_image_name, schedule_image_uuid, shcedule_image_created_at )
-- VALUES( 2, 2, 'https://url.kr/u1bsl4', 'jpg', 'cnt_1833_img01', 'uuid', 20230101130000 );
-- insert into schedule_image (schedule_image_id, schedule_id, shcedule_image_url, schedule_image_type, schedule_image_name, schedule_image_uuid, shcedule_image_created_at )
-- VALUES( 3, 3, 'https://url.kr/jzk8ev', 'jpg', 'AMJ', 'uuid', 20230101130000 );
-- insert into schedule_image (schedule_image_id, schedule_id, shcedule_image_url, schedule_image_type, schedule_image_name, schedule_image_uuid, shcedule_image_created_at )
-- VALUES( 4, 4, 'https://url.kr/4l92ia', 'jpg', '104565_72567_3258', 'uuid', 20230101010000 );
-- insert into schedule_image (schedule_image_id, schedule_id, shcedule_image_url, schedule_image_type, schedule_image_name, schedule_image_uuid, shcedule_image_created_at )
-- VALUES( 5, 5, 'https://url.kr/d15ko6', 'jpg', '31363897', 'uuid', 20230101010000 );



-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 1, 1, 2, null, '파이팅', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 2, 1, 1, 1, '고마워', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 3, 2, 3, null, '감기 조심해', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 4, 5, 3, null, '열공!', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 5, 5, 8, 4, '222', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 6, 5, 6, 5, '333', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 7, 4, 2, null, '같이 하자', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 8, 5, 3, null, '여기 어디에요?', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 9, 6, 5, null, 'test', now() );
-- insert into comment (comments_id,schedule_id,user_id,parents_comments_id,comment_content,comment_created_at )
-- VALUES( 10, 6, 5, 9, 'ddd', now() );



-- insert into chat_room ( chat_room_id, user_id ) VALUES( 1, 1);
-- insert into chat_room ( chat_room_id, user_id ) VALUES( 2, 1);
-- insert into chat_room ( chat_room_id, user_id ) VALUES( 3, 1);
-- insert into chat_room ( chat_room_id, user_id ) VALUES( 4, 1);
-- insert into chat_room ( chat_room_id, user_id ) VALUES( 5, 2);



-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 1, 1);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 1, 2);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 2, 1);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 2, 3);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 3, 1);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 3, 6);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 4, 1);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 4, 8);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 5, 2);
-- insert into chat_user (chat_room_id, user_id2 ) VALUES( 5, 3);




-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 1, 1, 1, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 2, 1, 2, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 3, 2, 1, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 4, 2, 3, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 5, 3, 1, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 6, 3, 6, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 7, 4, 1, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 8, 4, 8, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 9, 5, 2, now());
-- insert into joined_chat ( joined_chat_room_id, user_id, chat_room_id2, joined_chat_created_at )
-- VALUES( 10, 5, 3, now());




-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at)
-- VALUES( 1, 1, 1, '안녕', now() );
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at)
-- VALUES( 2, 1, 1, '안 잊었지?', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 3, 1, 2, '응, 2시 맞지?', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 4, 1, 1, '맞아, 나중에 보자', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 5, 3, 1, '2시 콜?', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 6, 3, 6, 'ㅇㅇ', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 7, 4, 1, '2시까지야. 늦지말기', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 8, 4, 8, '응.', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 9, 2, 2, '안녕하세요', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 10, 2, 1, '안녕하세요!', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 11, 5, 2, '안녕', now() );
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 12, 5, 3, '안녕~', now());
-- insert into chat_message ( chat_message_id, chat_room_id, user_id, chat_message_contents, chat_created_at  )
-- VALUES( 13, 5, 3, '잘잤어?', now());


-- insert into share_schedule ( share_schedule_key, user_id, schedule_id ) VALUES( 1, 1, 5);
-- insert into share_schedule ( share_schedule_key, user_id, schedule_id ) VALUES( 2, 2, 5);
-- insert into share_schedule ( share_schedule_key, user_id, schedule_id ) VALUES( 3, 6, 5);
-- insert into share_schedule ( share_schedule_key, user_id, schedule_id ) VALUES( 4, 8, 5);




-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 1, '#71DCFC', '갓생살기', 1);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 2, '#DC143C', '운동', 5);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 3, '#FFE11D', '*^^*', 2);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 4, '#228B22', 'test', 7);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 5, '#6E34DA', 'test2', 7);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 6, '#FF00FF', '쇼핑', 2);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 7, '#800000', '1', 3);
-- insert into category ( category_id, category_color, category_name, user_id )
-- VALUES( 8, '#EE82EE', '2', 3);