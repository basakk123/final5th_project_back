package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserRespDto {

    @Setter
    @Getter
    public static class UserNameRespDto {
        private String userName;

        public UserNameRespDto(User user) {
            this.userName = user.getUserName();
        }
    }
}
