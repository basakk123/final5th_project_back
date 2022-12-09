package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChat;

public class JoinedChatReqDto {

    @Setter
    @Getter
    public static class JoinedChatSaveReqDto {
        private Long joinedChatRoomId;
        private Long userId;
        private Long chatRoomId2;
        private Timestamp joinedChatCreatedAt;

        public JoinedChat toEntity() {
            JoinedChat joinedChat = JoinedChat.builder()
                    .joinedChatRoomId(joinedChatRoomId)
                    .userId(userId)
                    .chatRoomId2(chatRoomId2)
                    .joinedChatCreatedAt(joinedChatCreatedAt)
                    .build();
            return joinedChat;
        }
    }
}
