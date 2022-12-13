package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserRespDto {

    @Setter
    @Getter
    public static class JoinRespDto {
        private Long userId;
        private String userName;
        private String userEmail;
        private String userPhonenumber;
        private String userRealname;

        public JoinRespDto(User user) {
            this.userId = user.getUserId();
            this.userName = user.getUserName();
            this.userEmail = user.getUserEmail();
            this.userPhonenumber = user.getUserPhonenumber();
            this.userRealname = user.getUserRealname();
        }
    }

    @Setter
    @Getter
    public static class PasswordUpdateRespDto {
        private String userName;

        public PasswordUpdateRespDto(User user) {
            this.userName = user.getUserName();
        }
    }

    @Setter
    @Getter
    public static class UserRealnameRespDto {
        private String userRealname;

        public UserRealnameRespDto(User user) {
            this.userRealname = user.getUserRealname();
        }
    }
}
