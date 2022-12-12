package shop.mtcoding.final5th.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import shop.mtcoding.final5th.domain.joined_chat.JoinedChat;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChatRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.JoinedChatReqDto.JoinedChatSaveReqDto;

@Sql("classpath:db/truncate.sql") // 롤백 대신 사용 (auto_increment 초기화 + 데이터 비우기)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class JoinedChatApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JoinedChatRepository joinedChatRepository;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        User green = userRepository.save(newUser("green", "01012345678"));
        session = new MockHttpSession();
        session.setAttribute("loginUser", new LoginUser(1L, green));
        JoinedChat greenJoinedChat1 = joinedChatRepository.save(newJoinedChat(1L));
        JoinedChat greenJoinedChat2 = joinedChatRepository.save(newJoinedChat(2L));
    }

    @Test
    public void saveJoindedChat_test() throws Exception {
        // given
        Long userId = 1L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        JoinedChatSaveReqDto joinedChatSaveReqDto = new JoinedChatSaveReqDto();
        joinedChatSaveReqDto.setChatRoomId2(1L);
        joinedChatSaveReqDto.setJoinedChatCreatedAt(Timestamp.valueOf("2022-12-11 11:00:00.0"));
        String requestBody = om.writeValueAsString(joinedChatSaveReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(post("/s/api/user/" + userId + "/joinedchat").content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.chatRoomId2").value(1L));
    }

    @Test
    public void findJoindeChatListByUserId_test() throws Exception {
        // given
        Long userId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/joinedchat")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findJoinedChatDetail_test() throws Exception {
        // given
        Long userId = 1L;
        Long joinedChatRoomId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/joinedchat/" + joinedChatRoomId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void deleteByJoinedChatId_test() throws Exception {
        // given
        Long userId = 1L;
        Long joinedChatRoomId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(delete("/s/api/user/" + userId + "/joinedchat/" + joinedChatRoomId)
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }
}
