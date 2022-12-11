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
        private User user;

        public JoinRespDto(User user) {
            this.user = user;
        }
    }
}
