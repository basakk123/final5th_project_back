package shop.mtcoding.final5th.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class UserApiController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/user/userrealname/{userId}")
    public ResponseEntity<?> findUserRealnameById(@PathVariable Long userId) {
        log.debug("디버그 : findUserRealnameById 컨트롤러 실행됨");
        UserRealnameRespDto userRealnameRespDto = userService.findUserRealnameById(userId);
        log.debug("디버그 : findUserRealnameById 컨트롤러 리턴 전");
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "유저네임보기 성공", userRealnameRespDto), HttpStatus.OK);
    }
}
