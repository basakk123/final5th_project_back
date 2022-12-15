package shop.mtcoding.final5th.domain.follow;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.final5th.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "follow")
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long followingUserId;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Builder
    public Follow(Long followId, Long userId, Long followingUserId, Timestamp createdAt) {
        this.followId = followId;
        this.userId = userId;
        this.followingUserId = followingUserId;
        this.createdAt = createdAt;
    }
}
