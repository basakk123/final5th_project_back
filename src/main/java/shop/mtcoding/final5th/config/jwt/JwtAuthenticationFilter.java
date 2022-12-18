package shop.mtcoding.final5th.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.UserReqDto.LoginReqDto;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final UserRepository userRepository; // DI (FilterConfig 주입받음)

    // /login 요청시
    // post 요청시
    // username, password (json)
    // db확인
    // 토큰 생성
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Post요청이 아닌것을 거부
        if (!req.getMethod().equals("POST")) {
            customResponse("로그인시에는 post요청을 해야 합니다.", resp);
            return;
        }

        // Body 값 받기
        ObjectMapper om = new ObjectMapper();
        LoginReqDto loginReqDto = om.readValue(req.getInputStream(), LoginReqDto.class);
        log.debug("디버그 : " + loginReqDto.getUserName());
        log.debug("디버그 : " + loginReqDto.getUserPassword());

        // 유저네임 있는지 체크
        Optional<User> userOP = userRepository.findByUsername(loginReqDto.getUserName());
        if (userOP.isEmpty()) {
            customResponse("유저네임을 찾을 수 없습니다.", resp);
            return;
        }

        // 패스워드 체크
        // User userPS = userOP.get();
        // SHA256 sh = new SHA256();
        // String encPassword = sh.encrypt(loginReqDto.getPassword());
        // if (!userPS.getUserPassword().equals(encPassword)) {
        // customResponse("패스워드가 틀렸습니다.", resp);
        // return;
        // }
        User userPS = userOP.get();
        if (!userPS.getUserPassword().equals(loginReqDto.getUserPassword())) {
            customResponse("패스워드가 틀렸습니다.", resp);
            return;
        }

        // JWT토큰 생성
        String jwtToken = JwtProcess.create(new LoginUser(userPS));

        // JWT토큰 응답
        customJwtResponse(jwtToken, userPS, resp);

        // chain.doFilter(req, resp);
    }

    private void customJwtResponse(String token, User userPS, HttpServletResponse resp)
            throws IOException, JsonProcessingException {
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Authorization", "Bearer " + token);
        PrintWriter out = resp.getWriter();
        resp.setStatus(201);
        ResponseDto<?> responseDto = new ResponseDto<>(HttpStatus.CREATED, "성공", new LoginUser(userPS));
        ObjectMapper om = new ObjectMapper();
        String body = om.writeValueAsString(responseDto);
        out.println(body);
        out.flush();
    }

    private void customResponse(String msg, HttpServletResponse resp) throws IOException, JsonProcessingException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        resp.setStatus(400);
        ResponseDto<?> responseDto = new ResponseDto<>(HttpStatus.BAD_REQUEST, msg, null);
        ObjectMapper om = new ObjectMapper();
        String body = om.writeValueAsString(responseDto);
        out.println(body);
        out.flush();
    }

}