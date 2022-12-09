package shop.mtcoding.final5th.config.dummy;

import java.sql.Timestamp;

import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.todo.Todo;
import shop.mtcoding.final5th.domain.user.User;

public abstract class DummyEntity {

    protected User newUser(String userName) {
        User user = User.builder()
                .userName(userName)
                .userEmail(userName + "@nate.com")
                .userPhonenumber("01012345678")
                .userPassword("1234")
                .userRealname("그린")
                .build();
        return user;
    }

    protected Todo newTodo(String todoTitle) {
        Todo todo = Todo.builder()
                .userId(1L)
                .todoTitle(todoTitle)
                .todoFinished(false)
                .build();
        return todo;
    }

    protected Schedule newSchedule(String scheduleTitle) {
        Schedule schedule = Schedule.builder()
                .userId(1L)
                .scheduleTitle(scheduleTitle)
                .scheduleCreatedAt(Timestamp.valueOf("2022-12-11 11:00:00.0"))
                .build();
        return schedule;
    }

    protected Category newCategory(String categoryColor, String categoryName) {
        Category category = Category.builder()
                .categoryColor(categoryColor)
                .categoryName(categoryName)
                .userId(1L)
                .build();
        return category;
    }
}
