package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.news.News;
import shop.mtcoding.final5th.domain.news.NewsRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.NewsReqDto.NewsSaveReqDto;
import shop.mtcoding.final5th.dto.NewsRespDto.NewsListRespDto;
import shop.mtcoding.final5th.dto.NewsRespDto.NewsSaveRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NewsService {

        private final UserRepository userRepository;
        private final NewsRepository newsRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        @Transactional
        public NewsSaveRespDto saveNews(NewsSaveReqDto newsSaveReqDto) {
                News newsPS = newsRepository.save(newsSaveReqDto.toEntity());
                return new NewsSaveRespDto(newsPS);
        }

        public NewsListRespDto findNewsListByTargetUserId(Long targetUserId) {
                User userPS = userRepository.findById(targetUserId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                List<News> newsListPS = newsRepository.findNewsListByTargetUserId(targetUserId);
                return new NewsListRespDto(newsListPS);
        }

        @Transactional
        public void deleteByNewsId(Long userId, Long newsId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                News newsPS = newsRepository.findById(newsId)
                                .orElseThrow(() -> new CustomApiException("해당 알림이 없습니다", HttpStatus.BAD_REQUEST));
                newsRepository.deleteById(newsId);
        }
}
