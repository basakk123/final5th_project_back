package shop.mtcoding.final5th.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.auth.LoginUser;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.dto.NewsRespDto.NewsListRespDto;
import shop.mtcoding.final5th.dto.ResponseDto;
import shop.mtcoding.final5th.service.NewsService;
import shop.mtcoding.final5th.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/s/api")
@RestController
public class NewsApiController {

    private final UserService userService;
    private final NewsService newsService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final HttpSession session;

    @GetMapping("/user/{targetUserId}/news")
    public ResponseEntity<?> findNewsListByTargetUserId(@PathVariable Long targetUserId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != targetUserId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        NewsListRespDto newsListRespDto = newsService.findNewsListByTargetUserId(targetUserId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "알림 리스트 보기 성공", newsListRespDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/news/{newsId}")
    public ResponseEntity<?> deleteByNewsId(@PathVariable Long userId, @PathVariable Long newsId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser.getUserId() != userId) {
            throw new CustomApiException("권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        newsService.deleteByNewsId(userId, newsId);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "알림 삭제 성공", null),
                HttpStatus.OK);
    }
}
