package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleUpdateReqDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto.ScheduleDetailRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleUpdateRespDto;
import shop.mtcoding.final5th.service.ScheduleService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class ScheduleApiController {

    private final UserService userService;
    private final ScheduleService scheduleService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{userId}/schedule")
    public ResponseEntity<?> findScheduleListAndCategoryByUserId(@PathVariable Long userId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        ScheduleListRespDto scheduleListRespDto = scheduleService.findScheduleListAndCategoryByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "일정과 카테고리 리스트 보기 성공", scheduleListRespDto),
                HttpStatus.OK);
    }

    @GetMapping("user/{userId}/schedule/{scheduleId}")
    public ResponseEntity<?> findScheduleDetail(@PathVariable Long userId, @PathVariable Long scheduleId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        ScheduleDetailRespDto scheduleDetailRespDto = scheduleService.findScheduleDetail(userId, scheduleId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "스케줄 상세보기 성공", scheduleDetailRespDto),
                HttpStatus.OK);
    }

    @PostMapping("user/{userId}/schedule/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long userId, @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateReqDto scheduleUpdateReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        ScheduleUpdateRespDto scheduleUpdateRespDto = scheduleService.updateSchedule(userId, scheduleId,
                scheduleUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "스케줄 수정 성공", scheduleUpdateRespDto),
                HttpStatus.OK);
    }
}
