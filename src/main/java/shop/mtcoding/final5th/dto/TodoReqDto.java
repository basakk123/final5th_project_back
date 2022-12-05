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
        private boolean todoFinished;

        public Todo toEntity() {
            Todo todo = Todo.builder()
                    .userId(userId)
                    .todoTitle(todoTitle)
                    .todoFinished(todoFinished)
                    .build();
            return todo;
        }
    }

    @Setter
    @Getter
    public static class TodoUpdateReqDto {
        private String todoTitle;
        private boolean todoFinished;

        public Todo toEntity() {
            Todo todo = Todo.builder()
                    .todoTitle(todoTitle)
                    .todoFinished(todoFinished)
                    .build();
            return todo;
        }
    }
}
