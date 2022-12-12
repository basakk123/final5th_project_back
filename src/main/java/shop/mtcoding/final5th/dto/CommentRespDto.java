package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.comment.Comment;

public class CommentRespDto {

    @Setter
    @Getter
    public static class CommentListRespDto {
        private List<CommentDto> commentDtos;

        public CommentListRespDto(List<Comment> todoList) {
            this.commentDtos = todoList.stream().map((comment) -> new CommentDto(comment))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class CommentDto {
            private Long commentsId;
            private Long scheduleId;
            private Long userId;
            private Long parentsCommentsId;
            private String commentContent;
            private Timestamp commentCreatedAt;

            public CommentDto(Comment comment) {
                this.commentsId = comment.getCommentsId();
                this.scheduleId = comment.getScheduleId();
                this.userId = comment.getUserId();
                this.parentsCommentsId = comment.getParentsCommentsId();
                this.commentContent = comment.getCommentContent();
                this.commentCreatedAt = comment.getCommentCreatedAt();
            }
        }
    }

    @Setter
    @Getter
    public static class CommentDetailRespDto {
        private Long commentsId;
        private Long scheduleId;
        private Long userId;
        private Long parentsCommentsId;
        private String commentContent;
        private Timestamp commentCreatedAt;

        public CommentDetailRespDto(Comment comment) {
            this.commentsId = comment.getCommentsId();
            this.scheduleId = comment.getScheduleId();
            this.userId = comment.getUserId();
            this.parentsCommentsId = comment.getParentsCommentsId();
            this.commentContent = comment.getCommentContent();
            this.commentCreatedAt = comment.getCommentCreatedAt();
        }
    }

    @Setter
    @Getter
    public static class CommentSaveRespDto {
        private Long commentsId;
        private Long scheduleId;
        private Long userId;
        private Long parentsCommentsId;
        private String commentContent;
        private Timestamp commentCreatedAt;

        public CommentSaveRespDto(Comment comment) {
            this.commentsId = comment.getCommentsId();
            this.scheduleId = comment.getScheduleId();
            this.userId = comment.getUserId();
            this.parentsCommentsId = comment.getParentsCommentsId();
            this.commentContent = comment.getCommentContent();
            this.commentCreatedAt = comment.getCommentCreatedAt();
        }
    }

    @Setter
    @Getter
    public static class CommentUpdateRespDto {
        private Long scheduleId;
        private Long userId;
        private Long parentsCommentsId;
        private String commentContent;

        public CommentUpdateRespDto(Comment comment) {
            this.scheduleId = comment.getScheduleId();
            this.userId = comment.getUserId();
            this.parentsCommentsId = comment.getParentsCommentsId();
            this.commentContent = comment.getCommentContent();
        }
    }
}
