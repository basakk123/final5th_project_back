package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
import shop.mtcoding.final5th.service.ScheduleService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RestController
public class ScheduleApiControllerNoToken {

    private final UserService userService;
    private final ScheduleService scheduleService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{userId}/schedule")
    public ResponseEntity<?> findScheduleListByUserId2(@PathVariable Long userId) {
        ScheduleListRespDto scheduleListRespDto = scheduleService.findScheduleListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "스케줄 리스트 보기 성공", scheduleListRespDto),
                HttpStatus.OK);
    }
}
