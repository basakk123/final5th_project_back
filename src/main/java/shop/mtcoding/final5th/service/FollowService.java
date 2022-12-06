package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.FollowRespDto.FollowListRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FollowService {

        private final UserRepository userRepository;
        private final FollowRepository followRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        public FollowListRespDto findFollowListByFollowingUserId(Long followingUserId) {
                User userPS = userRepository.findById(followingUserId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                List<Follow> followListPS = followRepository.findFollowListByFollowingUserId(followingUserId);
                return new FollowListRespDto(followListPS);
        }
}
