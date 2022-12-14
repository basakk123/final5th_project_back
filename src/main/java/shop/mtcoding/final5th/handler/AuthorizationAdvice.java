package shop.mtcoding.final5th.handler;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;

@Component
@Aspect
public class AuthorizationAdvice {

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(shop.mtcoding.final5th.config.annotation.AuthorizationCheck)")
    public void authorizationCheck() {
    }

    @Around("authorizationCheck()")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 메서드 파라메터 변수명들
        String[] paramNames = ((CodeSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        // 메서드 파라메터 변수값들
        Object[] paramValues = proceedingJoinPoint.getArgs();
        Long userId = null;
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

        for (int i = 0; i < paramNames.length; i++) {
            if (paramNames[i].equals("userId")) {
                userId = (Long) paramValues[i];
            }
        }

        if (userId == null || loginUser == null) {
            throw new CustomApiException("로그인을 진행해주세요", HttpStatus.UNAUTHORIZED);
        }

        // 권한 확인
        if (userId != loginUser.getUserId()) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        return proceedingJoinPoint.proceed();
    }
}
