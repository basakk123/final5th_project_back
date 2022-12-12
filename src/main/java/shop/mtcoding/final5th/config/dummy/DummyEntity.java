package shop.mtcoding.final5th.config.dummy;

import java.sql.Timestamp;

import shop.mtcoding.final5th.domain.category.Category;
import shop.mtcoding.final5th.domain.follow.Follow;
import shop.mtcoding.final5th.domain.joined_chat.JoinedChat;
import shop.mtcoding.final5th.domain.news.News;
import shop.mtcoding.final5th.domain.schedule.Schedule;
import shop.mtcoding.final5th.domain.todo.Todo;
import shop.mtcoding.final5th.domain.user.User;

public abstract class DummyEntity {

    protected User newUser(String userName, String userPhonenumber) {
        User user = User.builder()
                .userName(userName)
                .userEmail(userName + "@nate.com")
                .userPhonenumber(userPhonenumber)
                .userPassword("1234")
                .userRealname("그린")
                .build();
        return user;
    }

    protected Todo newTodo(Long userId, String todoTitle) {
        Todo todo = Todo.builder()
                .userId(userId)
                .todoTitle(todoTitle)
                .todoFinished(false)
                .build();
        return todo;
    }

    protected Schedule newSchedule(Long userId, String scheduleTitle) {
        Schedule schedule = Schedule.builder()
                .userId(userId)
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

    protected News newNews(Long userId) {
        News news = News.builder()
                .targetUserId(1L)
                .userId(userId)
                .scheduleId(1L)
                .commentsId(1L)
                .followId(1L)
                .build();
        return news;
    }

    protected JoinedChat newJoinedChat(Long chatRoomId2) {
        JoinedChat joinedChat = JoinedChat.builder()
                .userId(1L)
                .chatRoomId2(chatRoomId2)
                .joinedChatCreatedAt(Timestamp.valueOf("2022-12-11 11:00:00.0"))
                .build();
        return joinedChat;
    }

    protected Follow newFollow(Long followingUserId, Long userId) {
        Follow follow = Follow.builder()
                .followingUserId(followingUserId)
                .userId(userId)
                .createdAt(Timestamp.valueOf("2022-12-11 11:00:00.0"))
                .build();
        return follow;
    }
}
