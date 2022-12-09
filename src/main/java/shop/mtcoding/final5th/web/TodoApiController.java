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

    @GetMapping("/user/{userId}/todo")
    public ResponseEntity<?> findTodoListByUserId(@PathVariable Long userId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        TodoListRespDto tododListRespDto = todoService.findTodoListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "투두 리스트 보기 성공", tododListRespDto),
                HttpStatus.OK);
    }

    // @GetMapping("/user/{followinguserid}/todo/{userId}")
    // public ResponseEntity<?> findTodoListByFollowingUserId(@PathVariable Long
    // followingUserId,
    // @PathVariable Long userId) {
    // LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    // if (loginUser.getUserId() != userId) {
    // throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
    // }
    // FollowingTodoListRespDto followingTodoListRespDto =
    // todoService.findTodoListByFollowingUserId(followingUserId,
    // userId);
    // return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "팔로잉 투두 리스트 보기
    // 성공", followingTodoListRespDto),
    // HttpStatus.OK);
    // }

    @GetMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> findTodoDetail(@PathVariable Long userId, @PathVariable Long todoId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        TodoDetailRespDto todoDetailRespDto = todoService.findTodoDetail(userId, todoId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "투두 상세보기 성공", todoDetailRespDto),
                HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/todo")
    public ResponseEntity<?> saveTodo(@PathVariable Long userId, @RequestBody TodoSaveReqDto todoSaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        log.debug("디버그 : loginUser " + loginUser);
        log.debug("디버그 : loginUser.getUserId() " + loginUser.getUserId());
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.FORBIDDEN);
        }
        todoSaveReqDto.setUserId(userId);
        TodoSaveRespDto todoSaveRespDto = todoService.saveTodo(todoSaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "스케줄 작성 성공", todoSaveRespDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable Long userId, @PathVariable Long todoId,
            @RequestBody TodoUpdateReqDto todoUpdateReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        TodoUpdateRespDto todoUpdateRespDto = todoService.updateTodo(userId, todoId,
                todoUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "투두 수정 성공", todoUpdateRespDto),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> deleteByTodoId(@PathVariable Long userId, @PathVariable Long todoId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        todoService.deleteByTodoId(userId, todoId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "투두 삭제 성공", null),
                HttpStatus.OK);
    }
}
