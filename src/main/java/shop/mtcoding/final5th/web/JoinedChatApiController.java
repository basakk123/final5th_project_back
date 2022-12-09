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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.JoinedChatReqDto.JoinedChatSaveReqDto;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatDetailRespDto;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatListRespDto;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatSaveRespDto;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.service.JoinedChatService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class JoinedChatApiController {

    private final UserService userService;
    private final JoinedChatService joinedChatService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @PostMapping("/user/{userId}/joinedchat")
    public ResponseEntity<?> saveJoindedChat(@PathVariable Long userId,
            @RequestBody JoinedChatSaveReqDto joinedChatSaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        log.debug("디버그 : loginUser " + loginUser);
        log.debug("디버그 : loginUser.getUserId() " + loginUser.getUserId());
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.FORBIDDEN);
        }
        joinedChatSaveReqDto.setUserId(userId);
        JoinedChatSaveRespDto joinedChatSaveRespDto = joinedChatService.saveJoindedChat(joinedChatSaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "채팅방 생성 성공", joinedChatSaveRespDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/joinedchat")
    public ResponseEntity<?> findJoindeChatListByUserId(@PathVariable Long userId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        JoinedChatListRespDto joinedChatListRespDto = joinedChatService.findJoindeChatListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "참여중인 채팅 리스트 보기 성공", joinedChatListRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/joinedchat/{joinedChatRoomId}")
    public ResponseEntity<?> findJoinedChatDetail(@PathVariable Long userId, @PathVariable Long joinedChatRoomId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        JoinedChatDetailRespDto joinedChatDetailRespDto = joinedChatService.findJoinedChatDetail(userId,
                joinedChatRoomId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "채팅 상세보기 성공", joinedChatDetailRespDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/joinedchat/{joinedchatId}")
    public ResponseEntity<?> deleteByJoinedChatId(@PathVariable Long userId, @PathVariable Long joinedChatId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        joinedChatService.deleteByJoinedChatId(userId, joinedChatId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "채팅방 삭제 성공", null),
                HttpStatus.OK);
    }
}
