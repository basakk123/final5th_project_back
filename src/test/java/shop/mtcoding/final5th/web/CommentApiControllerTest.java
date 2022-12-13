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
import shop.mtcoding.final5th.domain.comment.Comment;
import shop.mtcoding.final5th.domain.comment.CommentRepository;
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.schedule.ScheduleRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.CommentReqDto.CommentSaveReqDto;
import shop.mtcoding.final5th.dto.CommentReqDto.CommentUpdateReqDto;

@Sql("classpath:db/truncate.sql") // 롤백 대신 사용 (auto_increment 초기화 + 데이터 비우기)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CommentApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        User green = userRepository.save(newUser("green", "01012345678", "그린"));
        session = new MockHttpSession();
        session.setAttribute("loginUser", new LoginUser(1L, green));
        Schedule greenSchedule1 = scheduleRepository.save(newSchedule(1L, "자격증 시험"));
        Schedule greenSchedule2 = scheduleRepository.save(newSchedule(1L, "여행가기"));
        User orange = userRepository.save(newUser("orange", "01012341234", "오렌지"));
        Comment orangeComment1 = commentRepository.save(newComment(2L, "오 멋있다 ㅋㅋㅋㅋㅋㅋ"));
        Comment orangeComment2 = commentRepository.save(newComment(1L, "고마워!"));
    }

    @Test
    public void saveComment_test() throws Exception {
        // given
        Long userId = 1L;
        Long scheduleId = 1L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        CommentSaveReqDto commentSaveReqDto = new CommentSaveReqDto();
        commentSaveReqDto.setCommentContent("대박!");
        commentSaveReqDto.setCommentCreatedAt(Timestamp.valueOf("2022-12-11 11:00:00.0"));
        String requestBody = om.writeValueAsString(commentSaveReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(post("/s/api/user/" + userId + "/schedule/" + scheduleId + "/comment").content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void findCommentListByScheduleId_test() throws Exception {
        // given
        Long userId = 1L;
        Long scheduleId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/schedule/" + scheduleId + "/comment")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findCommentDetail_test() throws Exception {
        // given
        Long userId = 1L;
        Long commentId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/comment/" + commentId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void updateComment_test() throws Exception {
        // given
        Long userId = 1L;
        Long scheduleId = 1L;
        Long commentId = 2L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        CommentUpdateReqDto commentUpdateReqDto = new CommentUpdateReqDto();
        commentUpdateReqDto.setCommentContent("우와 ㅋㅋㅋㅋ");
        String requestBody = om.writeValueAsString(commentUpdateReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/user/" + userId + "/schedule/" + scheduleId + "/comment/" + commentId)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void deleteByCommentId_test() throws Exception {
        // given
        Long userId = 1L;
        Long commentId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(delete("/s/api/user/" + userId + "/comment/" + commentId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }
}
