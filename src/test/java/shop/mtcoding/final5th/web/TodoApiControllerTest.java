package shop.mtcoding.final5th.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.dummy.DummyEntity;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoSaveReqDto;

@Sql("classpath:db/truncate.sql") // 롤백 대신 사용 (auto_increment 초기화 + 데이터 비우기)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TodoApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    private MockHttpSession session;

    @BeforeEach
    public void sessionInit() {
        User green = userRepository.save(newUser("green"));
        session = new MockHttpSession();
        session.setAttribute("loginUser", new LoginUser(1L, newUser("green")));
    }

    @Test
    public void saveTodo_test() throws Exception {
        // given
        Long userId = 1L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        TodoSaveReqDto todoSaveReqDto = new TodoSaveReqDto();
        todoSaveReqDto.setUserId(userId);
        todoSaveReqDto.setTodoTitle("운동하기");
        todoSaveReqDto.setTodoFinished(false);
        String requestBody = om.writeValueAsString(todoSaveReqDto);
        System.out.println("테스트 : " + requestBody);
        // when
        ResultActions resultActions = mvc.perform(
                MockMvcRequestBuilders.post("/s/api/user/" + userId + "/todo").content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);
        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.todoTitle").value("운동하기"));
    }
}
