package shop.mtcoding.final5th.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.news.News;

public class NewsRespDto {

    @Setter
    @Getter
    public static class NewsListRespDto {
        private List<NewsDto> newsDtos;

        public NewsListRespDto(List<News> newsList) {
            this.newsDtos = newsList.stream().map((news) -> new NewsDto(news))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class NewsDto {
            private Long newsId;
            private Long targetUserId;
            private Long userId;
            private Long scheduleId;
            private Long commentsId;
            private Long followId;

            public NewsDto(News news) {
                this.newsId = news.getNewsId();
                this.targetUserId = news.getTargetUserId();
                this.userId = news.getUserId();
                this.scheduleId = news.getScheduleId();
                this.commentsId = news.getCommentsId();
                this.followId = news.getFollowId();
            }
        }
    }
}
