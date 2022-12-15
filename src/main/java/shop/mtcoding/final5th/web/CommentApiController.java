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
import shop.mtcoding.final5th.dto.CommentReqDto.CommentSaveReqDto;
import shop.mtcoding.final5th.dto.CommentReqDto.CommentUpdateReqDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentDetailRespDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentListRespDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentSaveRespDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentUpdateRespDto;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.service.CommentService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class CommentApiController {

    private final UserService userService;
    private final CommentService commentService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{userId}/schedule/{scheduleId}/comment")
    public ResponseEntity<?> findCommentListByScheduleId(@PathVariable Long userId, @PathVariable Long scheduleId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.FORBIDDEN);
        }
        CommentListRespDto commentListRespDto = commentService.findCommentListByScheduleId(userId, scheduleId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "코멘트 리스트 보기 성공", commentListRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity<?> findCommentDetail(@PathVariable Long userId, @PathVariable Long commentId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.FORBIDDEN);
        }
        CommentDetailRespDto commentDetailRespDto = commentService.findCommentDetail(userId, commentId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "코멘트 상세보기 성공", commentDetailRespDto),
                HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/schedule/{scheduleId}/comment")
    public ResponseEntity<?> saveComment(@PathVariable Long userId, @PathVariable Long scheduleId,
            @RequestBody CommentSaveReqDto commentSaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        log.debug("디버그 : loginUser " + loginUser);
        log.debug("디버그 : loginUser.getUserId() " + loginUser.getUserId());
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.FORBIDDEN);
        }
        commentSaveReqDto.setUserId(userId);
        commentSaveReqDto.setScheduleId(scheduleId);
        CommentSaveRespDto commentSaveRespDto = commentService.saveComment(commentSaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "코멘트 작성 성공", commentSaveRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @PutMapping("/user/{userId}/schedule/{scheduleId}/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long userId, @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateReqDto commentUpdateReqDto) {
        CommentUpdateRespDto commentUpdateRespDto = commentService.updateComment(userId, scheduleId, commentId,
                commentUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "코멘트 수정 성공", commentUpdateRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @DeleteMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity<?> deleteByCommentId(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteByCommentId(userId, commentId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "코멘트 삭제 성공", null),
                HttpStatus.OK);
    }
}
