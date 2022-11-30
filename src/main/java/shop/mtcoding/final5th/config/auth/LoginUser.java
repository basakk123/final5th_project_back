package shop.mtcoding.final5th.config.auth;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

@Setter
@Getter
public class LoginUser {
    private Long userId;
    private String userName;

    public LoginUser(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }

    public User toEntity() {
        return User.builder().userId(userId).userName(userName).build();
    }
}
