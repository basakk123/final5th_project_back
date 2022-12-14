package shop.mtcoding.final5th.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.todo.Todo;

public class TodoReqDto {

    @Setter
    @Getter
    public static class TodoSaveReqDto {
        private Long userId;
        private String todoTitle;
        private boolean isFinished;

        public Todo toEntity() {
            Todo todo = Todo.builder()
                    .userId(userId)
                    .todoTitle(todoTitle)
                    .isFinished(isFinished)
                    .build();
            return todo;
        }
    }

    @Setter
    @Getter
    public static class TodoUpdateReqDto {
        private Long userId;
        private String todoTitle;
        private boolean isFinished;

        public Todo toEntity() {
            Todo todo = Todo.builder()
                    .userId(userId)
                    .todoTitle(todoTitle)
                    .isFinished(isFinished)
                    .build();
            return todo;
        }
    }
}
