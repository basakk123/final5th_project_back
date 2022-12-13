package shop.mtcoding.final5th.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

public class UserReqDto {

    @Setter
    @Getter
    public static class JoinReqDto {
        @Size(min = 2, max = 20)
        @NotBlank(message = "유저네임은 필수입니다")
        private String userName;

        @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 영문, 숫자, 특수문자 최소 4에서 최대 20까지 입니다")
        private String userPassword;

        @NotBlank(message = "이메일은 필수입니다")
        private String userEmail;

        @NotBlank(message = "휴대폰 번호는 필수입니다")
        private String userPhonenumber;
        private String userRealname;

        public User toEntity() {
            return User.builder()
                    .userName(userName)
                    .userPassword(userPassword)
                    .userEmail(userEmail)
                    .userPhonenumber(userPhonenumber)
                    .userRealname(userRealname)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
