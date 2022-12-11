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
import shop.mtcoding.final5th.dto.UserReqDto.FindPasswordByEmailReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.FindPasswordByEmailRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.JoinRespDto;
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

    @Transactional
    public JoinRespDto joinUser(JoinReqDto joinReqDto) {
        log.debug("디버그 : 서비스 회원가입 실행됨");
        return new JoinRespDto(userRepository.save(joinReqDto.toEntity()));
    }

}
