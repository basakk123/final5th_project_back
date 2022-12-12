package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.comment.Comment;

public class CommentReqDto {

    @Setter
    @Getter
    public static class CommentSaveReqDto {
        private Long scheduleId;
        private Long userId;
        private Long parentsCommentsId;
        private String commentContent;
        private Timestamp commentCreatedAt;

        public Comment toEntity() {
            Comment comment = Comment.builder()
                    .scheduleId(scheduleId)
                    .userId(userId)
                    .parentsCommentsId(parentsCommentsId)
                    .commentContent(commentContent)
                    .commentCreatedAt(commentCreatedAt)
                    .build();
            return comment;
        }
    }

    @Setter
    @Getter
    public static class CommentUpdateReqDto {
        private Long scheduleId;
        private Long userId;
        private Long parentsCommentsId;
        private String commentContent;
        private Timestamp commentCreatedAt;

        public Comment toEntity() {
            Comment comment = Comment.builder()
                    .scheduleId(scheduleId)
                    .userId(userId)
                    .parentsCommentsId(parentsCommentsId)
                    .commentContent(commentContent)
                    .commentCreatedAt(commentCreatedAt)
                    .build();
            return comment;
        }
    }
}
