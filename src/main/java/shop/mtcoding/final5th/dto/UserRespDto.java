package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserRespDto {

    @Setter
    @Getter
    public static class UserRealnameRespDto {
        private String userRealname;

        public UserRealnameRespDto(User user) {
            this.userRealname = user.getUserRealname();
        }
    }
}
