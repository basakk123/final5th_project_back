package shop.mtcoding.final5th.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserReqDto {

    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

    @Setter
    @Getter
    public static class JoinReqDto {

        @Size(min = 2, max = 20)
        @NotBlank(message = "유저네임은 필수입니다.")
        private String userName;
        @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 영문,숫자,특수문자 최소4에서 최대20까지입니다.")
        private String userPassword;
        private String userRealname;
        private String userImgfile;
        private String userProfileIntro;
        @Size(min = 10, max = 30)
        @NotBlank(message = "이메일은 필수입니다.")
        private String userEmail;
        private String userPhonenumber;
        private Timestamp userCreatedAt;
        private Timestamp userUpdatedAt;
        private String userWebLink;
        private String userImageUrl;
        private String userImageType;
        private String userImageName;
        private String userImageUuid;

        public User toEntity() {
            return User.builder()
                    .userName(userName)
                    .userPassword(userPassword)
                    .userRealname(userRealname)
                    .userImgfile(userImgfile)
                    .userProfileIntro(userProfileIntro)
                    .userName(userEmail)
                    .userPhonenumber(userPhonenumber)
                    .userCreatedAt(userCreatedAt)
                    .userUpdatedAt(userUpdatedAt)
                    .userWebLink(userWebLink)
                    .userImageUrl(userImageUrl)
                    .userImageType(userImageType)
                    .userImageName(userImageName)
                    .userImageUuid(userImageUuid)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class findByEmail {
        private Long userId;
        private String userEmail;

        public User toEntity() {
            User user = User.builder()
                    .userId(userId)
                    .userEmail(userEmail)
                    .build();
            return user;
        }
    }

    @Setter
    @Getter
    public static class UserUpdateReqDto {
        private Long userId;
        private String userImgfile;
        private String userName;
        private String userProfileIntro;

        public User toEntity() {
            User user = User.builder()
                    .userId(userId)
                    .userImgfile(userImgfile)
                    .userEmail(userImgfile)
                    .userProfileIntro(userProfileIntro)
                    .build();
            return user;
        }
    }

}
