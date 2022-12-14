package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.annotation.AuthorizationCheck;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleSaveReqDto;
import shop.mtcoding.final5th.dto.ScheduleReqDto.ScheduleUpdateReqDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.FollowingScheduleListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleDetailRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleSaveRespDto;
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

    @AuthorizationCheck
    @GetMapping("/user/{userId}/schedule")
    public ResponseEntity<?> findScheduleListByUserId(@PathVariable Long userId) {
        ScheduleListRespDto scheduleListRespDto = scheduleService.findScheduleListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "????????? ????????? ?????? ??????", scheduleListRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/{followingUserId}/following/schedule/{userId}")
    public ResponseEntity<?> findFollowingScheduleListByUserId(@PathVariable Long followingUserId,
            @PathVariable Long userId) {
        log.debug("????????? : ???????????? ?????????");
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != followingUserId) {
            throw new CustomApiException("????????? ????????????", HttpStatus.FORBIDDEN);
        }
        FollowingScheduleListRespDto followingScheduleListRespDto = scheduleService
                .findFollowingScheduleListByUserId(followingUserId, userId);
        log.debug("????????? : ???????????? ?????? ???");
        return new ResponseEntity<>(
                new ResponseDto<>(HttpStatus.OK, "????????? ????????? ????????? ?????? ??????",
                        followingScheduleListRespDto),
                HttpStatus.OK);
    }

    @AuthorizationCheck
    @GetMapping("/user/{userId}/schedule/{scheduleId}")
    public ResponseEntity<?> findScheduleDetail(@PathVariable Long userId, @PathVariable Long scheduleId) {
        ScheduleDetailRespDto scheduleDetailRespDto = scheduleService.findScheduleDetail(userId, scheduleId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "????????? ???????????? ??????", scheduleDetailRespDto),
                HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/schedule")
    public ResponseEntity<?> saveSchedule(@PathVariable Long userId,
            @RequestBody ScheduleSaveReqDto scheduleSaveReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() == null) {
            throw new CustomApiException("???????????? ??????????????????", HttpStatus.FORBIDDEN);
        }
        scheduleSaveReqDto.setUserId(userId);
        log.debug("????????? : scheduleSaveReqDto.getUserId() " + scheduleSaveReqDto.getUserId());
        ScheduleSaveRespDto scheduleSaveRespDto = scheduleService.saveSchedule(scheduleSaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "????????? ?????? ??????", scheduleSaveRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @PutMapping("/user/{userId}/schedule/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long userId, @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateReqDto scheduleUpdateReqDto) {
        ScheduleUpdateRespDto scheduleUpdateRespDto = scheduleService.updateSchedule(userId, scheduleId,
                scheduleUpdateReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "????????? ?????? ??????", scheduleUpdateRespDto),
                HttpStatus.CREATED);
    }

    @AuthorizationCheck
    @DeleteMapping("/user/{userId}/schedule/{scheduleId}")
    public ResponseEntity<?> deleteByScheduleId(@PathVariable Long userId, @PathVariable Long scheduleId) {
        scheduleService.deleteByScheduleId(userId, scheduleId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "????????? ?????? ??????", null),
                HttpStatus.OK);
    }
}
