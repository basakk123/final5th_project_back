package shop.mtcoding.final5th.web;

import java.util.List;

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
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.FollowCountRespDto;
import shop.mtcoding.final5th.dto.FollowListRespDto;
import shop.mtcoding.final5th.dto.FollowerCountRespDto;
import shop.mtcoding.final5th.dto.FollowerListRespDto;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.service.FollowService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class FollowApiController {

    private final UserService userService;
    private final FollowService followService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{followingUserId}/follow")
    public ResponseEntity<?> findFollowListByFollowingUserId(@PathVariable Long followingUserId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != followingUserId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        List<FollowListRespDto> followListRespDtos = followService.findFollowListByFollowingUserId(followingUserId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "팔로우 리스트 보기 성공",
                followListRespDtos),
                HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/follower")
    public ResponseEntity<?> findFollowerListByUserId(@PathVariable Long userId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        List<FollowerListRespDto> followerListRespDtos = followService.findFollowerListByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "팔로워 리스트 보기 성공",
                followerListRespDtos),
                HttpStatus.OK);
    }

    @GetMapping("/user/{followingUserId}/follow/count")
    public ResponseEntity<?> findFollowCountByFollowingUserId(@PathVariable Long followingUserId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != followingUserId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        FollowCountRespDto followCountRespDto = followService.findFollowCountByFollowingUserId(followingUserId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "팔로우 개수 보기 성공",
                followCountRespDto),
                HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/follower/count")
    public ResponseEntity<?> findFollowerCountByUserId(@PathVariable Long userId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        FollowerCountRespDto followerCountRespDto = followService.findFollowerCountByUserId(userId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "팔로워 개수 보기 성공",
                followerCountRespDto),
                HttpStatus.OK);
    }
}
