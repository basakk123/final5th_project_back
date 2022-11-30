package shop.mtcoding.final5th.domain.comment;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
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

    @Column(nullable = false)
    private Timestamp commentCreatedAt;
}
