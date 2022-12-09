package shop.mtcoding.final5th.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.category.CategoryRepository;
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
            ScheduleRepository scheduleRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            User green = userRepository.save(newUser("green"));
            Todo greenTodo1 = todoRepository.save(newTodo("운동하기"));
            Todo greenTodo2 = todoRepository.save(newTodo("공부하기"));
            Schedule greenSchedule1 = scheduleRepository.save(newSchedule("자격증시험"));
            Schedule greenSchedule2 = scheduleRepository.save(newSchedule("여행가기"));
            Category greenCategory1 = categoryRepository.save(newCategory("yellow", "운동"));
            Category greenCategory2 = categoryRepository.save(newCategory("red", "쇼핑"));
        };
    }
}
