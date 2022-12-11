package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserRespDto {

    @Setter
    @Getter
    public static class UserRealnameRespDto {
        private String userName;

        public UserRealnameRespDto(User user) {
            this.userName = user.getUserRealname();
        }
    }

    @Getter
    @Setter
    public static class JoinRespDto {
        private Long userId;

        public JoinRespDto(User user) {
            this.userId = user.getUserId();
        }
    }

    @Getter
    @Setter
    public static class findByEmail {
        private Long userId;
        private String userPassword;

        public findByEmail(User user) {
            this.userId = user.getUserId();
            this.userPassword = user.getUserPassword();
        }
    }

    @Setter
    @Getter
    public static class UserUpdateRespDto {
        private String userImgfile;
        private String userName;
        private String userProfileIntro;

        public UserUpdateRespDto(User user) {
            this.userImgfile = user.getUserImgfile();
            this.userName = user.getUserName();
            this.userProfileIntro = user.getUserProfileIntro();
        }
    }
}
