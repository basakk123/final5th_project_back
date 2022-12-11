package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.UserUpdateReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.JoinRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserUpdateRespDto;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class UserApiController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @PostMapping("/join")
    public ResponseEntity<?> joinApi(@RequestBody JoinReqDto joinReqDto) {
        log.debug("디버그 : UserApiController join 실행됨");
        JoinRespDto joinRespDto = userService.joinUser(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "회원 가입 성공", joinRespDto),
                HttpStatus.OK);
    }

    // @GetMapping("/user/{userId}/findpassword")
    // public void findByEmail(@PathVariable Long userId, String userEmail) {
    // LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
    // if (loginUser.getUserId() != userId) {
    // throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
    // }
    // userService.findByEmail(userId, userEmail);
    // }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateReqDto userUpdateReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        UserUpdateRespDto userUpdateRespDto = userService.updateUser(userId, userUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "유저 수정 성공", userUpdateRespDto),
                HttpStatus.CREATED);
    }
}
