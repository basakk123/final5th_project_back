package shop.mtcoding.final5th.web;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.UserReqDto.EmailCheckReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;
import shop.mtcoding.final5th.service.EmailService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class UserApiController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;
    private final EmailService emailService;

    @GetMapping("/user/userrealname/{userId}")
    public ResponseEntity<?> findUserRealnameById(@PathVariable Long userId) {
        log.debug("디버그 : findUserRealnameById 컨트롤러 실행됨");
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        UserRealnameRespDto userRealnameRespDto = userService.findUserRealnameById(userId);
        log.debug("디버그 : findUserRealnameById 컨트롤러 리턴 전");
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "유저네임보기 성공", userRealnameRespDto), HttpStatus.OK);
    }

    @GetMapping("/board/imageForm")
    public String imageForm() {
        return "board/imageForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto dto, Model model) {
        userService.회원가입(dto);

        session.setAttribute("principal", dto);
        return "redirect:/joinConfirm";
    }

    @GetMapping("/joinConfirm")
    public String joinConfirm() {

        return "user/joinConfirm";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/user/email/send")
    public void sendmail(EmailCheckReqDto dto) throws MessagingException {

        StringBuffer emailcontent = new StringBuffer();

        emailcontent.append("<!DOCTYPE html>");
        emailcontent.append("<html>");
        emailcontent.append("<head>");
        emailcontent.append("</head>");
        emailcontent.append("<body>");
        emailcontent.append(
                " <div>" +
                        dto.getUsername() +
                        "		님 안녕하세요.<br />" +
                        "		아래 메일 인증 버튼을 클릭하여 회원가입을 완료해 주세요.<br />" +
                        "		감사합니다.<br />" +
                        "	<a" +
                        "	href=\"http://localhost:8000/user/email/certified?username=" + dto.getUsername()
                        + "&emailConfirm=" + dto.getUserEmailConfirm() + "\" target=\"_blank\">" +
                        "		<button> 메일 인증 </button>" +
                        "	</a>" +
                        " </div>");
        emailcontent.append("</body>");
        emailcontent.append("</html>");

        emailService.sendEmail(dto.getUserEmail(), "메일 인증", emailcontent.toString());
    }

    @GetMapping(value = "/user/email/certified")
    public String checkmail(EmailCheckReqDto dto) throws MessagingException {

        User u = userService.이메일인증확인(dto);

        if (u != null) {
            userService.이메일인증업데이트(dto);
            session.invalidate(); // 로그아웃
        } else {
            System.out.println("실패");
        }

        return "user/emailSuccess";
    }
}
