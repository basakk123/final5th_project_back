package shop.mtcoding.final5th.dto;

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
        private String userEmail;
        private String userName;
        private String userPassword;

        public User toEntity() {
            return User.builder()
                    .userEmail(this.userEmail)
                    .userName(this.userEmail)
                    .userPassword(this.userPassword)
                    .build();
        }
    }

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

}
