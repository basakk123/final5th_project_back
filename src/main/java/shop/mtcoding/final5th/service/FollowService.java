package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.follow.FollowRepositoryQuery;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.FollowCountRespDto;
import shop.mtcoding.final5th.dto.FollowListRespDto;
import shop.mtcoding.final5th.dto.FollowerListRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FollowService {

        private final UserRepository userRepository;
        private final FollowRepository followRepository;
        private final FollowRepositoryQuery followRepositoryQuery;
        private final Logger log = LoggerFactory.getLogger(getClass());

        public List<FollowListRespDto> findFollowListByFollowingUserId(Long followingUserId) {
                User userPS = userRepository.findById(followingUserId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다",
                                                HttpStatus.BAD_REQUEST));
                List<FollowListRespDto> followListRespDtos = followRepositoryQuery
                                .findFollowListByFollowingUserId(followingUserId);
                return followListRespDtos;
        }

        public List<FollowerListRespDto> findFollowerListByUserId(Long userId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다",
                                                HttpStatus.BAD_REQUEST));
                List<FollowerListRespDto> followerListRespDtos = followRepositoryQuery
                                .findFollowerListByUserId(userId);
                return followerListRespDtos;
        }

        public FollowCountRespDto findFollowCountByFollowingUserId(Long followingUserId) {
                User userPS = userRepository.findById(followingUserId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다",
                                                HttpStatus.BAD_REQUEST));
                FollowCountRespDto followCountRespDto = followRepositoryQuery
                                .findFollowCountByFollowingUserId(followingUserId);
                return followCountRespDto;
        }
}
