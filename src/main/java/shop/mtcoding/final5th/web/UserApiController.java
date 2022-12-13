package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.PasswordUpdateReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.JoinRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.PasswordUpdateRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserListRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinReqDto joinReqDto) {
        log.debug("디버그 : join 실행됨");
        JoinRespDto joinRespDto = userService.join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "회원가입 성공", joinRespDto), HttpStatus.CREATED);
    }

    @PutMapping("/s/api/user/{userId}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Long userId,
            @RequestBody PasswordUpdateReqDto passwordUpdateReqDto) {
        log.debug("디버그 : 비밀번호 수정 컨트롤러 실행됨");
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        log.debug("디버그 :  loginUser.getUserId() " + loginUser.getUserId());
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        PasswordUpdateRespDto passwordUpdateRespDto = userService.updatePassword(userId, passwordUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "비밀번호 수정 성공", passwordUpdateRespDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/s/api/user/{userId}/userrealname")
    public ResponseEntity<?> findUserRealnameById(@PathVariable Long userId) {
        log.debug("디버그 : findUserRealnameById 컨트롤러 실행됨");
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        log.debug("디버그 : loginUser " + loginUser);
        log.debug("디버그 : loginUser.getUserId() " + loginUser.getUserId());
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        UserRealnameRespDto userRealnameRespDto = userService.findUserRealnameById(userId);
        log.debug("디버그 : findUserRealnameById 컨트롤러 리턴 전");
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "유저리얼네임보기 성공", userRealnameRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/list")
    public ResponseEntity<?> findUserList() {
        UserListRespDto userListRespDto = userService.findUserList();
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "유저 전체 리스트 보기 성공", userListRespDto),
                HttpStatus.OK);
    }
}
