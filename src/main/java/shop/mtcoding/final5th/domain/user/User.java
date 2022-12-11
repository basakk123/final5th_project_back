package shop.mtcoding.final5th.domain.user;

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
import shop.mtcoding.final5th.domain.AudingTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "users")
@Entity
public class User extends AudingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false, length = 50)
    private String userName;
    @Column(nullable = false, length = 1000)
    private String userPassword;
    @Column(length = 50)
    private String userRealname;
    @Column(length = 255)
    private String userImgfile;
    @Column(length = 255)
    private String userProfileIntro;
    @Column(unique = true, nullable = false, length = 255)
    private String userEmail;
    @Column(unique = true, length = 50)
    private String userPhonenumber;
    @Column(nullable = false)
    private Timestamp userCreatedAt;
    @Column(nullable = false)
    private Timestamp userUpdatedAt;
    @Column(length = 255)
    private String userWebLink;
    @Column(length = 255)
    private String userImageUrl;
    @Column(length = 255)
    private String userImageType;
    @Column(length = 255)
    private String userImageName;
    @Column(length = 255)
    private String userImageUuid;

    @Builder
    public User(Long userId, String userName, String userEmail, String userPhonenumber, String userPassword,
            String userRealname, String userImgfile, String userProfileIntro, Timestamp userCreatedAt,
            Timestamp userUpdatedAt, String userWebLink, String userImageUrl,
            String userImageType, String userImageName, String userImageUuid) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhonenumber = userPhonenumber;
        this.userPassword = userPassword;
        this.userRealname = userRealname;
        this.userImgfile = userImgfile;
        this.userProfileIntro = userProfileIntro;
        this.userWebLink = userWebLink;
        this.userImageUrl = userImageUrl;
        this.userImageType = userImageType;
        this.userImageName = userImageName;
        this.userImageUuid = userImageUuid;
    }
}
