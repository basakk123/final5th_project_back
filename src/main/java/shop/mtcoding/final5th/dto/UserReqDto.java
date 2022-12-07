package shop.mtcoding.final5th.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserReqDto {

    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class JoinReqDto {
        @Size(min = 2, max = 20)
        @NotBlank
        private String username;

        @Size(min = 4, max = 20)
        @NotBlank
        private String userPassword;

        @Size(min = 4, max = 50)
        @NotBlank
        private String userEmail;

        private String userEmailConfirm = "confirmNot";

        public User toEntity() {
            User user = User.builder()
                    .userName(username)
                    .userPassword(userPassword)
                    .userEmail(userEmail)
                    .userEmailConfirm(userEmailConfirm)
                    .build();
            return user;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class EmailCheckReqDto {

        @Size(min = 2, max = 20)
        @NotBlank
        private String username;

        @Size(min = 4, max = 20)
        @NotBlank
        private String userEmail;

        @Size(min = 4, max = 20)
        @NotBlank
        private String userEmailConfirm;

    }
}
