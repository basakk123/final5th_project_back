package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.comment.Comment;
import shop.mtcoding.final5th.domain.comment.CommentRepository;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.CommentReqDto.CommentSaveReqDto;
import shop.mtcoding.final5th.dto.CommentReqDto.CommentUpdateReqDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentDetailRespDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentListRespDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentSaveRespDto;
import shop.mtcoding.final5th.dto.CommentRespDto.CommentUpdateRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

        private final UserRepository userRepository;
        private final CommentRepository commentRepository;
        private final FollowRepository followRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        public CommentListRespDto findCommentListByScheduleId(Long userId, Long scheduleId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                List<Comment> commentListPS = commentRepository.findCommentListByScheduleId(scheduleId);
                return new CommentListRespDto(commentListPS);
        }

        public CommentDetailRespDto findCommentDetail(Long userId, Long commentId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Comment commentPS = commentRepository.findById(commentId)
                                .orElseThrow(() -> new CustomApiException("해당 코멘트가 없습니다", HttpStatus.BAD_REQUEST));
                return new CommentDetailRespDto(commentPS);
        }

        @Transactional
        public CommentSaveRespDto saveComment(CommentSaveReqDto commentSaveReqDto) {
                Comment CommentPS = commentRepository.save(commentSaveReqDto.toEntity());
                return new CommentSaveRespDto(CommentPS);
        }

        @Transactional
        public CommentUpdateRespDto updateComment(Long userId, Long scheduleId, Long commentId,
                        CommentUpdateReqDto commentUpdateReqDto) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Comment commentPS = commentRepository.findById(commentId)
                                .orElseThrow(() -> new CustomApiException("해당 코멘트가 없습니다", HttpStatus.BAD_REQUEST));
                commentUpdateReqDto.setUserId(userId);
                commentUpdateReqDto.setScheduleId(scheduleId);
                Comment comment = commentUpdateReqDto.toEntity();
                commentPS = commentRepository.save(comment);
                return new CommentUpdateRespDto(commentPS);
        }

        @Transactional
        public void deleteByCommentId(Long userId, Long commentId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Comment commentPS = commentRepository.findById(commentId)
                                .orElseThrow(() -> new CustomApiException("해당 코멘트가 없습니다", HttpStatus.BAD_REQUEST));
                commentRepository.deleteById(commentId);
        }
}
