package shop.mtcoding.final5th.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.category.CategoryRepository;
import shop.mtcoding.final5th.domain.comment.Comment;
import shop.mtcoding.final5th.domain.comment.CommentRepository;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.follow.FollowRepository;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChat;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChatRepository;
import shop.mtcoding.final5th.domain.news.News;
import shop.mtcoding.final5th.domain.news.NewsRepository;
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.schedule.ScheduleRepository;
import shop.mtcoding.final5th.domain.todo.Todo;
import shop.mtcoding.final5th.domain.todo.TodoRepository;
import shop.mtcoding.final5th.domain.user.User;
import shop.mtcoding.final5th.domain.user.UserRepository;

@RequiredArgsConstructor
@Configuration
public class DevInit extends DummyEntity {

    @Profile("dev")
    @Bean
    public CommandLineRunner dataSetting(UserRepository userRepository, TodoRepository todoRepository,
            ScheduleRepository scheduleRepository, CategoryRepository categoryRepository,
            NewsRepository newsRepository, JoinedChatRepository joinedChatRepository,
            FollowRepository followRepository, CommentRepository commentRepository) {
        return (args) -> {
            User green = userRepository.save(newUser("green", "01012345678"));
            Todo greenTodo1 = todoRepository.save(newTodo(1L, "운동하기"));
            Todo greenTodo2 = todoRepository.save(newTodo(1L, "공부하기"));
            Schedule greenSchedule1 = scheduleRepository.save(newSchedule(1L, "자격증시험"));
            Schedule greenSchedule2 = scheduleRepository.save(newSchedule(1L, "여행가기"));
            Category greenCategory1 = categoryRepository.save(newCategory("yellow", "운동"));
            Category greenCategory2 = categoryRepository.save(newCategory("red", "쇼핑"));
            News greenNews1 = newsRepository.save(newNews(2L));
            News greenNews2 = newsRepository.save(newNews(3L));
            JoinedChat greenJoinedChat1 = joinedChatRepository.save(newJoinedChat(1L));
            JoinedChat greenJoinedChat2 = joinedChatRepository.save(newJoinedChat(2L));
            User orange = userRepository.save(newUser("orange", "01012341234"));
            Todo orangeTodo1 = todoRepository.save(newTodo(2L, "운동하기"));
            Todo orangeTodo2 = todoRepository.save(newTodo(2L, "공부하기"));
            Schedule orangeSchedule1 = scheduleRepository.save(newSchedule(2L, "자격증시험"));
            Schedule orangeSchedule2 = scheduleRepository.save(newSchedule(2L, "여행가기"));
            Follow greenFollow1 = followRepository.save(newFollow(1L, 2L));
            Comment orangeComment1 = commentRepository.save(newComment(2L, "오 멋있다 ㅋㅋㅋㅋㅋㅋ"));
            Comment orangeComment2 = commentRepository.save(newComment(1L, "고마워!"));
        };
    }
}
