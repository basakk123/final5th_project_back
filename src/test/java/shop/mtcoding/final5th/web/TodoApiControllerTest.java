package shop.mtcoding.final5th.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.dummy.DummyEntity;
import shop.mtcoding.final5th.domain.todo.Todo;
import shop.mtcoding.final5th.domain.todo.TodoRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoSaveReqDto;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoUpdateReqDto;

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

    @Autowired
    private TodoRepository todoRepository;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        User green = userRepository.save(newUser("green"));
        session = new MockHttpSession();
        session.setAttribute("loginUser", new LoginUser(1L, newUser("green")));
        Todo greenTodo1 = todoRepository.save(newTodo("운동하기"));
        Todo greenTodo2 = todoRepository.save(newTodo("공부하기"));
    }

    @Test
    public void saveTodo_test() throws Exception {
        // given
        Long userId = 1L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        TodoSaveReqDto todoSaveReqDto = new TodoSaveReqDto();
        todoSaveReqDto.setTodoTitle("운동하기");
        todoSaveReqDto.setTodoFinished(false);
        String requestBody = om.writeValueAsString(todoSaveReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(post("/s/api/user/" + userId + "/todo").content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.todoTitle").value("운동하기"));
    }

    @Test
    public void findTodoListByUserId_test() throws Exception {
        // given
        Long userId = 1L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/todo")
                        .accept(APPLICATION_JSON_UTF8)
                        .session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void findTodoDetail_test() throws Exception {
        // given
        Long userId = 1L;
        Long todoId = 2L;
        session.getAttribute("loginUser");

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/api/user/" + userId + "/todo/" + todoId)
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
        Long todoId = 2L;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println("테스트 : " + loginUser.getUserId());
        System.out.println("테스트 : " + loginUser.getUserName());
        TodoUpdateReqDto todoUpdateReqDto = new TodoUpdateReqDto();
        todoUpdateReqDto.setTodoTitle("공부하기");
        todoUpdateReqDto.setTodoFinished(true);
        String requestBody = om.writeValueAsString(todoUpdateReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/user/" + userId + "/todo/" + todoId).content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8).session(session));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.todoFinished").value(true));
    }
}
