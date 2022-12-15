package shop.mtcoding.final5th.domain.comment;

import java.sql.Timestamp;

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
@Table(name = "comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentsId;

    @Column(nullable = false)
    private Long scheduleId;
    @Column(nullable = false)
    private Long userId;

    private Long parentsCommentsId;

    @Column(nullable = false, length = 255)
    private String commentContent;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp commentCreatedAt;

    @Builder
    public Comment(Long commentsId, Long scheduleId, Long userId, Long parentsCommentsId, String commentContent,
            Timestamp commentCreatedAt) {
        this.commentsId = commentsId;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.parentsCommentsId = parentsCommentsId;
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
    }
}
