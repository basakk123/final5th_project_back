package shop.mtcoding.final5th.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.UserReqDto.EmailCheckReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.LoginReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public UserRealnameRespDto findUserRealnameById(Long userId) {
        log.debug("디버그 : findUserRealnameById 서비스 실행됨");
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
        log.debug("디버그 : findUserRealnameById 서비스 리턴 전");
        return new UserRealnameRespDto(userPS);
    }

    public void 회원가입(JoinReqDto dto) {
        userRepository.save(dto.toEntity());

    }

    public User 로그인(LoginReqDto dto) {
        User userEntity = userRepository.mLogin(dto.getUsername(), dto.getPassword());
        return userEntity;
    }

    public User 이메일인증확인(EmailCheckReqDto dto) {
        User userEntity = userRepository.mCheck(dto.getUsername(), dto.getUserEmailConfirm());
        return userEntity;
    }

    public void 이메일인증업데이트(EmailCheckReqDto dto) {
        userRepository.mUpdate(dto.getUsername());
    }
}
