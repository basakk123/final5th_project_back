package shop.mtcoding.final5th.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.dto.AllRespDto;
import shop.mtcoding.final5th.dto.FollowListRespDto;
import shop.mtcoding.final5th.dto.FollowerListRespDto;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatListRespDto;
import shop.mtcoding.final5th.dto.NewsRespDto.NewsListRespDto;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoListRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;
import shop.mtcoding.final5th.service.CommentService;
import shop.mtcoding.final5th.service.FollowService;
import shop.mtcoding.final5th.service.JoinedChatService;
import shop.mtcoding.final5th.service.NewsService;
import shop.mtcoding.final5th.service.ScheduleService;
import shop.mtcoding.final5th.service.TodoService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RestController
public class TodoApiControllerNoToken {

    private final UserService userService;
    private final ScheduleService scheduleService;
    private final TodoService todoService;
    private final FollowService followService;
    private final JoinedChatService joinedChatService;
    private final CommentService commentService;
    private final NewsService newsService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{userId}/todo")
    public ResponseEntity<?> findTodoListByUserId2(@PathVariable Long userId) {
        TodoListRespDto todoListRespDto = todoService.findTodoListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "투두 리스트 보기 성공", todoListRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/userrealname111")
    public ResponseEntity<?> findUserRealnameById222(@PathVariable Long userId) {
        UserRealnameRespDto userRealnameRespDto = userService.findUserRealnameById(userId);
        ScheduleListRespDto scheduleListRespDto = scheduleService.findScheduleListByUserId(userId);
        TodoListRespDto todoListRespDto = todoService.findTodoListByUserId(userId);
        List<FollowListRespDto> followListRespDtos = followService.findFollowListByFollowingUserId(userId);
        List<FollowerListRespDto> followerListRespDtos = followService.findFollowerListByUserId(userId);
        JoinedChatListRespDto joinedChatListRespDto = joinedChatService.findJoindeChatListByUserId(userId);
        NewsListRespDto newsListRespDto = newsService.findNewsListByTargetUserId(userId);
        AllRespDto allRespDto = new AllRespDto(userRealnameRespDto, scheduleListRespDto, todoListRespDto,
                followListRespDtos, followerListRespDtos, joinedChatListRespDto, null, newsListRespDto);

        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "전체 데이터 보기 성공", allRespDto),
                HttpStatus.OK);
    }
}
