package shop.mtcoding.final5th.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

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
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.schedule.ScheduleRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleSaveReqDto;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleUpdateReqDto;

@Sql("classpath:db/truncate.sql") // 롤백 대신 사용 (auto_increment 초기화 + 데이터 비우기)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ScheduleApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FollowRepository followRepository;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        User green = userRepository.save(newUser("green", "01012345678", "그린"));
        session = new MockHttpSession();
        session.setAttribute("loginUser", new LoginUser(1L, green));
        Schedule greenSchedule1 = scheduleRepository.save(newSchedule(1L, "자격증 시험"));
        Schedule greenSchedule2 = scheduleRepository.save(newSchedule(1L, "여행가기"));
        User orange = userRepository.save(newUser("orange", "01012341234", "오렌지"));
        Schedule orangeSchedule1 = scheduleRepository.save(newSchedule(2L, "자격증시험"));
        Schedule orangeSchedule2 = scheduleRepository.save(newSchedule(2L, "여행가기"));
        Follow greenFollow1 = followRepository.save(newFollow(1L, 2L));
    }

    @Test
    public void saveSchedule_test() throws Exception {
        // given
        Long userId = 1L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        ScheduleSaveReqDto scheduleSaveReqDto = new ScheduleSaveReqDto();
        scheduleSaveReqDto.setScheduleTitle("자격증시험");
        scheduleSaveReqDto.setScheduleCreatedAt(Timestamp.valueOf("2022-12-11 11:00:00.0"));
        String requestBody = om.writeValueAsString(scheduleSaveReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(post("/s/api/user/" + userId + "/schedule").content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.scheduleTitle").value("자격증시험"));
    }

    @Test
    public void findTodoListByUserId_test() throws Exception {
        // given
        Long userId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/schedule")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findFollowingScheduleListByUserId_test() throws Exception {
        // given
        Long followingUserId = 1L;
        Long userId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + followingUserId + "/following/schedule/" + userId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findScheduleDetail_test() throws Exception {
        // given
        Long userId = 1L;
        Long scheduleId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/schedule/" + scheduleId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void updateTodo_test() throws Exception {
        // given
        Long userId = 1L;
        Long scheduleId = 2L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        ScheduleUpdateReqDto scheduleUpdateReqDto = new ScheduleUpdateReqDto();
        scheduleUpdateReqDto.setScheduleTitle("여행가기 (은지랑)");
        scheduleUpdateReqDto.setScheduleCreatedAt(Timestamp.valueOf("2022-12-11 11:00:00.0"));
        String requestBody = om.writeValueAsString(scheduleUpdateReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/user/" + userId + "/schedule/" + scheduleId).content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.scheduleTitle").value("여행가기 (은지랑)"));
    }

    @Test
    public void deleteByScheduleId_test() throws Exception {
        // given
        Long userId = 1L;
        Long scheduleId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(delete("/s/api/user/" + userId + "/schedule/" + scheduleId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }
}
