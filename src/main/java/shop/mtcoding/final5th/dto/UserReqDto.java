package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;

public class UserReqDto {

    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
