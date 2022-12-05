package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChat;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChatRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatDetailRespDto;
import shop.mtcoding.final5th.dto.JoinedChatRespDto.JoinedChatListRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class JoinedChatService {

        private final UserRepository userRepository;
        private final JoinedChatRepository joinedChatRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        public JoinedChatListRespDto findJoindeChatListByUserId(Long userId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                List<JoinedChat> joinedChatListPS = joinedChatRepository.findJoinedChatListByUserId(userId);
                return new JoinedChatListRespDto(joinedChatListPS);
        }

        public JoinedChatDetailRespDto findJoinedChatDetail(Long userId, Long joinedChatId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                JoinedChat joinedChatPS = joinedChatRepository.findById(joinedChatId)
                                .orElseThrow(() -> new CustomApiException("해당 투두가 없습니다", HttpStatus.BAD_REQUEST));
                return new JoinedChatDetailRespDto(joinedChatPS);
        }
}
