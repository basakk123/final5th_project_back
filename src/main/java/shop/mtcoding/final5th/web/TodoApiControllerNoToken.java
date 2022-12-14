package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoListRespDto;
import shop.mtcoding.final5th.service.TodoService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RestController
public class TodoApiControllerNoToken {

    private final UserService userService;
    private final TodoService todoService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{userId}/todo")
    public ResponseEntity<?> findTodoListByUserId2(@PathVariable Long userId) {
        TodoListRespDto todoListRespDto = todoService.findTodoListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "투두 리스트 보기 성공", todoListRespDto),
                HttpStatus.OK);
    }
}
