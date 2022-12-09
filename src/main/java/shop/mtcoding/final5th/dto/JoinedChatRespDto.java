package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChat;

public class JoinedChatRespDto {

    @Setter
    @Getter
    public static class JoinedChatSaveRespDto {
        private Long joinedChatRoomId;
        private Long userId;
        private Long chatRoomId2;
        private Timestamp joinedChatCreatedAt;

        public JoinedChatSaveRespDto(JoinedChat joinedChat) {
            this.joinedChatRoomId = joinedChat.getJoinedChatRoomId();
            this.userId = joinedChat.getUserId();
            this.chatRoomId2 = joinedChat.getChatRoomId2();
            this.joinedChatCreatedAt = joinedChat.getJoinedChatCreatedAt();
        }
    }

    @Setter
    @Getter
    public static class JoinedChatListRespDto {
        private List<JoinedChatDto> joinedChatDtos;

        public JoinedChatListRespDto(List<JoinedChat> joinedChatList) {
            this.joinedChatDtos = joinedChatList.stream().map((joinedChat) -> new JoinedChatDto(joinedChat))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class JoinedChatDto {
            private Long joinedChatRoomId;
            private Long userId;
            private Long chatRoomId2;
            private Timestamp joinedChatCreatedAt;

            public JoinedChatDto(JoinedChat joinedChat) {
                this.joinedChatRoomId = joinedChat.getJoinedChatRoomId();
                this.userId = joinedChat.getUserId();
                this.chatRoomId2 = joinedChat.getChatRoomId2();
                this.joinedChatCreatedAt = joinedChat.getJoinedChatCreatedAt();
            }
        }
    }

    @Setter
    @Getter
    public static class JoinedChatDetailRespDto {
        private Long joinedChatRoomId;
        private Long userId;
        private Long chatRoomId2;
        private Timestamp joinedChatCreatedAt;

        public JoinedChatDetailRespDto(JoinedChat joinedChat) {
            this.joinedChatRoomId = joinedChat.getJoinedChatRoomId();
            this.userId = joinedChat.getUserId();
            this.chatRoomId2 = joinedChat.getChatRoomId2();
            this.joinedChatCreatedAt = joinedChat.getJoinedChatCreatedAt();
        }
    }
}
