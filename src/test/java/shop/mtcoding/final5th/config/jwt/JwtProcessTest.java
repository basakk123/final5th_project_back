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
        String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyIiwiZXhwIjoxNjcxOTMxNzMyLCJ1c2VyTmFtZSI6InNzYXIiLCJ1c2VySWQiOjF9.zrKU5j2ZBLkfJRqVhK6s8c5MrfjSpR1KTpjfNi2uYjf_Km69X9GM4jF-bfvmpUa4rRyQO-JFyJWVp02S8_QfeA";
        // when
        Long userId = JwtProcess.verify(jwtToken);
        // then
        System.out.println(userId);
    }
}
