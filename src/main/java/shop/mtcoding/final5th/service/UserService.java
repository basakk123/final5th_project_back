package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.final5th.dto.UserReqDto.PasswordUpdateReqDto;
import shop.mtcoding.final5th.dto.UserRespDto.JoinRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.PasswordUpdateRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserListRespDto;
import shop.mtcoding.final5th.dto.UserRespDto.UserRealnameRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional
    public JoinRespDto join(JoinReqDto joinReqDto) {
        log.debug("디버그 : 회원가입 실행됨");
        // String rawPassword = joinReqDto.getPassword();
        // String encPassword = passwordEncoder.encode(rawPassword);
        // joinReqDto.setPassword(encPassword);
        // log.debug("디버그 : encPassword" + encPassword);
        User userPS = userRepository.save(joinReqDto.toEntity());
        return new JoinRespDto(userPS);
    }

    @Transactional
    public PasswordUpdateRespDto updatePassword(Long userId, PasswordUpdateReqDto passwordUpdateReqDto) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
        log.debug("디버그 : userPS.getUserPassword() " + userPS.getUserPassword());
        log.debug("디버그 : passwordUpdateReqDto.getUserBeforePassword() " + passwordUpdateReqDto.getUserBeforePassword());
        if (!(userPS.getUserPassword().equals(passwordUpdateReqDto.getUserBeforePassword()))) {
            throw new CustomApiException("비밀번호가 틀렸습니다", HttpStatus.BAD_REQUEST);
        }
        passwordUpdateReqDto.setUserId(userId);
        passwordUpdateReqDto.setUserEmail(userPS.getUserEmail());
        passwordUpdateReqDto.setUserPhonenumber(userPS.getUserPhonenumber());
        passwordUpdateReqDto.setUserCreatedAt(userPS.getUserCreatedAt());
        User user = passwordUpdateReqDto.toEntity();
        userPS = userRepository.save(user);
        return new PasswordUpdateRespDto(userPS);
    }

    public UserRealnameRespDto findUserRealnameById(Long userId) {
        log.debug("디버그 : findUserRealnameById 서비스 실행됨");
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
        log.debug("디버그 : findUserRealnameById 서비스 리턴 전");
        return new UserRealnameRespDto(userPS);
    }

    public UserListRespDto findUserList() {
        List<User> userList = userRepository.findAll();
        return new UserListRespDto(userList);
    }
}
