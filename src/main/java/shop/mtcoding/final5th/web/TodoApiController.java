package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.annotation.AuthorizationCheck;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoSaveReqDto;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoUpdateReqDto;
import shop.mtcoding.final5th.dto.TodoRespDto.FollowingTodoListRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoDetailRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoListRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoSaveRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoUpdateRespDto;
import shop.mtcoding.final5th.service.TodoService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class TodoApiController {

    private final UserService userService;
    private final TodoService todoService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @AuthorizationCheck
    @GetMapping("/user/{userId}/todo")
    public ResponseEntity<?> findTodoListByUserId(@PathVariable Long userId) {
        TodoListRespDto todoListRespDto = todoService.findTodoListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "?????? ????????? ?????? ??????", todoListRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/{followingUserId}/following/todo/{userId}")
    public ResponseEntity<?> findFollowingTodoListByUserId(@PathVariable Long followingUserId,
            @PathVariable Long userId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != followingUserId) {
            throw new CustomApiException("????????? ????????????", HttpStatus.FORBIDDEN);
        }
        FollowingTodoListRespDto followingTodoListRespDto = todoService.findFollowingTodoListByUserId(followingUserId,
                userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "????????? ?????? ????????? ?????? ??????", followingTodoListRespDto),
                HttpStatus.OK);
    }

    @AuthorizationCheck
    @GetMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> findTodoDetail(@PathVariable Long userId, @PathVariable Long todoId) {
        TodoDetailRespDto todoDetailRespDto = todoService.findTodoDetail(userId, todoId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "?????? ???????????? ??????", todoDetailRespDto),
                HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/todo")
    public ResponseEntity<?> saveTodo(@PathVariable Long userId, @RequestBody TodoSaveReqDto todoSaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        log.debug("????????? : loginUser " + loginUser);
        log.debug("????????? : loginUser.getUserId() " + loginUser.getUserId());
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("???????????? ??????????????????", HttpStatus.FORBIDDEN);
        }
        todoSaveReqDto.setUserId(userId);
        TodoSaveRespDto todoSaveRespDto = todoService.saveTodo(todoSaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "?????? ?????? ??????", todoSaveRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @PutMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable Long userId, @PathVariable Long todoId,
            @RequestBody TodoUpdateReqDto todoUpdateReqDto) {
        TodoUpdateRespDto todoUpdateRespDto = todoService.updateTodo(userId, todoId,
                todoUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "?????? ?????? ??????", todoUpdateRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @DeleteMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> deleteByTodoId(@PathVariable Long userId, @PathVariable Long todoId) {
        todoService.deleteByTodoId(userId, todoId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "?????? ?????? ??????", null),
                HttpStatus.OK);
    }
}
