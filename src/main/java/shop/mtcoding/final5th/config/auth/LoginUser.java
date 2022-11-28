package shop.mtcoding.final5th.config.auth;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.user.User;

@Setter
@Getter
public class LoginUser {
    private Long id;
    private String username;

    public LoginUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public User toEntity() {
        return User.builder().id(id).username(username).build();
    }
}
