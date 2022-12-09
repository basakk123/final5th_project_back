package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.news.News;

public class NewsReqDto {

    @Setter
    @Getter
    public static class NewsSaveReqDto {
        private Long newsId;
        private Long targetUserId;
        private Long userId;
        private Long scheduleId;
        private Long commentsId;
        private Long followId;

        public News toEntity() {
            News news = News.builder()
                    .newsId(newsId)
                    .targetUserId(targetUserId)
                    .userId(userId)
                    .scheduleId(scheduleId)
                    .commentsId(commentsId)
                    .followId(followId)
                    .build();
            return news;
        }
    }
}
