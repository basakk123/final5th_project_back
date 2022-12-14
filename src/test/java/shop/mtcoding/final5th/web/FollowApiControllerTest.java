package shop.mtcoding.final5th.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.dummy.DummyEntity;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.todo.TodoRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;

@Sql("classpath:db/truncate.sql") // 롤백 대신 사용 (auto_increment 초기화 + 데이터 비우기)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class FollowApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private FollowRepository followRepository;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        User green = userRepository.save(newUser("green", "01012345678", "그린"));
        session = new MockHttpSession();
        session.setAttribute("loginUser", new LoginUser(1L, green));
        User orange = userRepository.save(newUser("orange", "01012341234", "오렌지"));
        User yellow = userRepository.save(newUser("yellow", "01012345578", "노랑"));
        Follow greenFollow1 = followRepository.save(newFollow(1L, 2L));
        Follow greenFollow2 = followRepository.save(newFollow(1L, 3L));
        Follow orangeFollow1 = followRepository.save(newFollow(2L, 1L));
        Follow yellowFollow1 = followRepository.save(newFollow(3L, 1L));
    }

    @Test
    public void findFollowListByFollowingUserId_test() throws Exception {
        // given
        Long followingUserId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + followingUserId + "/follow")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findFollowerListByUserId_test() throws Exception {
        // given
        Long userId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/follower")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findFollowCountByFollowingUserId_test() throws Exception {
        // given
        Long followingUserId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + followingUserId + "/follow/count")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findFollowerCountByUserId_test() throws Exception {
        // given
        Long userId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/follower/count")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }
}
