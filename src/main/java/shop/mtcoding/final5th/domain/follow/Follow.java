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

import lombok.Getter;
import shop.mtcoding.final5th.domain.user.User;

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

    @Column(nullable = false)
    private Timestamp createdAt;

    @OneToOne(mappedBy = "follow", fetch = FetchType.LAZY)
    private User user;
}
