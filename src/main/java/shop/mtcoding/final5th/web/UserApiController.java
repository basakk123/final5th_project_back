package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.JoinRespDto;
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
        JoinRespDto joinRespDto = userService.joinUser(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "회원 가입 성공", joinRespDto),
                HttpStatus.OK);
    }

}
