package shop.mtcoding.final5th.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.config.exception.CustomApiException;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.todo.Todo;
import shop.mtcoding.final5th.domain.todo.TodoRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoSaveReqDto;
import shop.mtcoding.final5th.dto.TodoReqDto.TodoUpdateReqDto;
import shop.mtcoding.final5th.dto.TodoRespDto.FollowingTodoListRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoDetailRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoListRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoSaveRespDto;
import shop.mtcoding.final5th.dto.TodoRespDto.TodoUpdateRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoService {

        private final UserRepository userRepository;
        private final TodoRepository todoRepository;
        private final FollowRepository followRepository;
        private final Logger log = LoggerFactory.getLogger(getClass());

        public TodoListRespDto findTodoListByUserId(Long userId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                List<Todo> todoListPS = todoRepository.findTodoListByUserId(userId);
                return new TodoListRespDto(todoListPS);
        }

        // public FollowingTodoListRespDto findTodoListByFollowingUserId(Long
        // followingUserId, Long userId) {
        // User userPS = userRepository.findById(userId)
        // .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다",
        // HttpStatus.BAD_REQUEST));
        // Follow followPS = followRepository.checkFollowing(followingUserId, userId)
        // .orElseThrow(() -> new CustomApiException("팔로잉하지 않은 유저입니다",
        // HttpStatus.BAD_REQUEST));
        // List<Todo> todoListPS = todoRepository.findTodoListByUserId(userId);
        // return new FollowingTodoListRespDto(todoListPS);
        // }

        public TodoDetailRespDto findTodoDetail(Long userId, Long todoId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Todo todoPS = todoRepository.findById(todoId)
                                .orElseThrow(() -> new CustomApiException("해당 투두가 없습니다", HttpStatus.BAD_REQUEST));
                return new TodoDetailRespDto(todoPS);
        }

        @Transactional
        public TodoSaveRespDto saveTodo(TodoSaveReqDto todoSaveReqDto) {
                Todo TodoPS = todoRepository.save(todoSaveReqDto.toEntity());
                return new TodoSaveRespDto(TodoPS);
        }

        @Transactional
        public TodoUpdateRespDto updateTodo(Long userId, Long todoId,
                        TodoUpdateReqDto todoUpdateReqDto) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Todo todoPS = todoRepository.findById(todoId)
                                .orElseThrow(() -> new CustomApiException("해당 투두가 없습니다", HttpStatus.BAD_REQUEST));
                Todo todo = todoUpdateReqDto.toEntity();
                todoPS = todoRepository.save(todo);
                return new TodoUpdateRespDto(todoPS);
        }

        @Transactional
        public void deleteByTodoId(Long userId, Long todoId) {
                User userPS = userRepository.findById(userId)
                                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.BAD_REQUEST));
                Todo todoPS = todoRepository.findById(todoId)
                                .orElseThrow(() -> new CustomApiException("해당 투두가 없습니다", HttpStatus.BAD_REQUEST));
                todoRepository.deleteById(todoId);
        }
}
