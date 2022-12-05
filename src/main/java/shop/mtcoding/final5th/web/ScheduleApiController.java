package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.dto.ScheduleRespDto.ScheduleListRespDto;
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

    @GetMapping("/schedule/{userId}")
    public ResponseEntity<?> findScheduleListAndCategoryByUserId(@PathVariable Long userId) {
        ScheduleListRespDto scheduleListRespDto = scheduleService.findScheduleListAndCategoryByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "일정과 카테고리 보기 성공", scheduleListRespDto),
                HttpStatus.OK);
    }
}
