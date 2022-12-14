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
import shop.mtcoding.final5th.dto.UserReqDto.ProfileUpdateReqDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false, length = 50)
    private String userName;
    @Column(unique = true, nullable = false, length = 255)
    private String userEmail;
    @Column(unique = true, nullable = false, length = 50)
    private String userPhonenumber;
    @Column(nullable = false, length = 1000)
    private String userPassword;
    @Column(length = 50)
    private String userRealname;
    @Column(length = 255)
    private String userImgfile;
    @Column(length = 255)
    private String userProfileIntro;
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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp userCreatedAt;

    @Column(updatable = true)
    private Timestamp userUpdatedAt;

    @Builder
    public User(Long userId, String userName, String userEmail, String userPhonenumber, String userPassword,
            String userRealname, String userImgfile, String userProfileIntro, String userWebLink, String userImageUrl,
            String userImageType, String userImageName, String userImageUuid, Timestamp userCreatedAt,
            Timestamp userUpdatedAt) {
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
        this.userCreatedAt = userCreatedAt;
        this.userUpdatedAt = userUpdatedAt;
    }

    public void updateProfile(ProfileUpdateReqDto profileUpdateReqDto) {
        this.userRealname = profileUpdateReqDto.getUserRealname();
        this.userImgfile = profileUpdateReqDto.getUserImgfile();
        this.userProfileIntro = profileUpdateReqDto.getUserProfileIntro();
    }
}
