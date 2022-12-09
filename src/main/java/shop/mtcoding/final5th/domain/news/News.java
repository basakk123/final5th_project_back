package shop.mtcoding.final5th.domain.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "news")
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(nullable = false)
    private Long targetUserId;

    @Column(nullable = false)
    private Long userId;

    private Long scheduleId;

    private Long commentsId;

    private Long followId;

    @Builder
    public News(Long newsId, Long targetUserId, Long userId, Long scheduleId, Long commentsId, Long followId) {
        this.newsId = newsId;
        this.targetUserId = targetUserId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.commentsId = commentsId;
        this.followId = followId;
    }
}
