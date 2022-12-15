package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.config.jwt.JwtProcess;
import shop.mtcoding.final5th.config.jwt.JwtProps;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserTokenRespDto;
import shop.mtcoding.final5th.service.UserService;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/jwtToken")
    public ResponseEntity<?> jwtToken(HttpServletRequest request) {

        String jwtToken = request.getHeader("authorization");
        if (jwtToken == null) {
            throw new CustomApiException("토큰이 헤더에 없습니다.", HttpStatus.ACCEPTED);
        }
        System.out.println("토큰이 헤더 있습니다." + jwtToken);
        jwtToken = jwtToken.replace(JwtProps.AUTH, "");

        Long userId = JwtProcess.verify(jwtToken);
        System.out.println("아이디가 있는지 봐야겠소 ," + userId);
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("토큰 검증 실패", HttpStatus.ACCEPTED));
        System.out.println(userPS.getUserName());
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, jwtToken, new UserTokenRespDto(userPS)),
                HttpStatus.OK);
    }
}
