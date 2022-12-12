package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.category.CategoryRepository;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.schedule.ScheduleRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleSaveReqDto;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleUpdateReqDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.FollowingScheduleListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleDetailRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleSaveRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleUpdateRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {

        private final UserRepository userRepository;
        private final ScheduleRepository scheduleRepository;
        private final CategoryRepository categoryRepository;
        private final FollowRepository followRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        public ScheduleListRespDto findScheduleListByUserId(Long userId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다",
                                                HttpStatus.BAD_REQUEST));
                List<Schedule> scheduleListPS = scheduleRepository.findScheduleListByUserId(userId);
                return new ScheduleListRespDto(scheduleListPS);
        }

        public FollowingScheduleListRespDto findFollowingScheduleListByUserId(Long followingUserId,
                        Long userId) {
                User userPS = userRepository.findById(followingUserId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다",
                                                HttpStatus.BAD_REQUEST));
                Follow followPS = followRepository.checkFollowing(followingUserId, userId)
                                .orElseThrow(() -> new CustomApiException("팔로잉하지 않은 유저입니다",
                                                HttpStatus.BAD_REQUEST));
                List<Schedule> scheduleListPS = scheduleRepository.findScheduleListByUserId(userId);
                return new FollowingScheduleListRespDto(scheduleListPS);
        }

        public ScheduleDetailRespDto findScheduleDetail(Long userId, Long scheduleId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Schedule SchedulePS = scheduleRepository.findById(scheduleId)
                                .orElseThrow(() -> new CustomApiException("해당 스케줄이 없습니다", HttpStatus.BAD_REQUEST));
                return new ScheduleDetailRespDto(SchedulePS);
        }

        @Transactional
        public ScheduleSaveRespDto saveSchedule(ScheduleSaveReqDto scheduleSaveReqDto) {
                Schedule SchedulePS = scheduleRepository.save(scheduleSaveReqDto.toEntity());
                return new ScheduleSaveRespDto(SchedulePS);
        }

        @Transactional
        public ScheduleUpdateRespDto updateSchedule(Long userId, Long scheduleId,
                        ScheduleUpdateReqDto scheduleUpdateReqDto) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Schedule SchedulePS = scheduleRepository.findById(scheduleId)
                                .orElseThrow(() -> new CustomApiException("해당 스케줄이 없습니다", HttpStatus.BAD_REQUEST));
                scheduleUpdateReqDto.setUserId(userId);
                Schedule Schedule = scheduleUpdateReqDto.toEntity();
                SchedulePS = scheduleRepository.save(Schedule);
                return new ScheduleUpdateRespDto(SchedulePS);
        }

        @Transactional
        public void deleteByScheduleId(Long userId, Long scheduleId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Schedule SchedulePS = scheduleRepository.findById(scheduleId)
                                .orElseThrow(() -> new CustomApiException("해당 스케줄이 없습니다", HttpStatus.BAD_REQUEST));
                scheduleRepository.deleteById(scheduleId);
        }
}
