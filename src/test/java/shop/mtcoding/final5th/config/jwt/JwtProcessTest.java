package shop.mtcoding.final5th.config.jwt;

import org.junit.jupiter.api.Test;

import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.domain.user.User;

public class JwtProcessTest {
    @Test
    public void create_test() {
        // given
        User user = User.builder().userId(1L).userName("ssar").build();
        LoginUser loginUser = new LoginUser(user);
        String token = JwtProcess.create(loginUser);
        System.out.println(token);
    }

    @Test
    public void verify_test() throws Exception {
        // given

        // when

        // then

    }
}
