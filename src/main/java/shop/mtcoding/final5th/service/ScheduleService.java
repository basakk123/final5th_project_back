package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.category.CategoryRepository;
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.schedule.ScheduleRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleUpdateReqDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto.ScheduleDetailRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleUpdateRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public ScheduleListRespDto findScheduleListAndCategoryByUserId(Long userId) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
        List<Schedule> scheduleListPS = scheduleRepository.findScheduleListByUserId(userId);
        List<Category> categoryListPS = categoryRepository.findCategoryListByUserId(userId);
        return new ScheduleListRespDto(scheduleListPS, categoryListPS);
    }

    public ScheduleDetailRespDto findScheduleDetail(Long userId, Long scheduleId) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
        Schedule SchedulePS = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomApiException("해당 스케줄이 없습니다", HttpStatus.BAD_REQUEST));
        return new ScheduleDetailRespDto(SchedulePS);
    }

    @Transactional
    public ScheduleUpdateRespDto updateSchedule(Long userId, Long scheduleId,
            ScheduleUpdateReqDto scheduleUpdateReqDto) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
        Schedule SchedulePS = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomApiException("해당 스케줄이 없습니다", HttpStatus.BAD_REQUEST));
        Schedule Schedule = scheduleUpdateReqDto.toEntity();
        SchedulePS = scheduleRepository.save(Schedule);
        return new ScheduleUpdateRespDto(SchedulePS);
    }
}
